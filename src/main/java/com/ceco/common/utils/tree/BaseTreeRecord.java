package com.ccj.common.utils.tree;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseTreeRecord<T extends BaseTreeRecord<T>> implements Serializable {

	private static final long serialVersionUID = -8801184678669144088L;

	public abstract String key();

	public abstract String parentKey();

	public abstract void addChild(T resp);

	public abstract void addFullPath(String fullCode);

	public abstract List<String> listFullPath();

	public abstract List<T> getChildren();

	public abstract void setChildren(List<T> ts);

	public static <T extends BaseTreeRecord<T>> List<T> buildTree(String rootCode, List<T> respList) {
		List<T> rootList = new ArrayList<>();
		respList.stream().map((resp) -> {
			if (StringUtils.isBlank(resp.parentKey()) || Objects.equals(rootCode, resp.parentKey())) {
				rootList.add(resp);
			}
			return resp;
		}).forEachOrdered((resp) -> {
			respList.stream().filter((child) -> (Objects.equals(child.parentKey(), resp.key())))
					.forEachOrdered((child) -> {
						resp.addChild(child);
					});
		});
		return rootList;
	}

	/**
	 * 构建部分根节点的树
	 * 
	 * @param <T>
	 * @param rootCodes
	 * @param respList
	 * @return
	 */
	public static <T extends BaseTreeRecord<T>> List<T> buildTreeByRoots(List<String> rootCodes, List<T> respList) {
		respList.stream().forEachOrdered((resp) -> {
			respList.stream().filter((child) -> (Objects.equals(child.parentKey(), resp.key())))
					.forEachOrdered((child) -> {
						resp.addChild(child);
					});
		});

		// 设置每个节点的全路径
		respList.stream().forEach(item -> {
			setFullPath(item);
		});

		// 按FullPath 全路径长度排序
		Comparator<T> com = (a, b) -> Integer.valueOf(a.listFullPath().size()).compareTo(b.listFullPath().size());
		List<T> respOrderList = respList.stream().sorted(com).collect(Collectors.toList());
		
		// 得到不一致的根节点,如果path中的根节点path，不一致，则新起一个节点
		HashSet<String> level1Code = new HashSet<>();

		respOrderList.stream().map((resp) -> {
			List<String> root = resp.listFullPath();
			// 无指定根节点
			if (rootCodes == null) {
				// 当根节点的fullPath长度为0时，就是根节点
				if (!CollectionUtils.isEmpty(root) && root.size() == 1) {
					// 根节点
					level1Code.add(resp.key());
				}
			} else if (rootCodes.contains(resp.key())) {
				boolean isExist = false;// 需要输出的根节点是否存在
				for (String code : level1Code) {
					if (resp.listFullPath().contains(code)) {
						isExist = true;
					}
				}
				if (!isExist) {
					level1Code.add(resp.key());
				}
			}
			return resp;
		}).collect(Collectors.toList());

		return respList.stream().filter(item->level1Code.contains(item.key())).collect(Collectors.toList());
	}

	/**
	 * 把树转成平铺结构
	 * 
	 * @param root
	 * @param list
	 */
	public static <T extends BaseTreeRecord<T>> void flatTree(T root, List<T> list) {
		List<T> result = Optional.ofNullable(root.getChildren()).orElseGet(() -> new ArrayList<T>()).stream().map(x -> {
			flatTree(x, list);
			x.setChildren(new ArrayList<>());
			if(!CollectionUtils.isEmpty(x.listFullPath())) {
				x.listFullPath().clear();
			}
			return x;
		}).collect(Collectors.toList());
		list.addAll(result);
	}

	/**
	 * 把树转成平铺结构，加上根节点
	 * 
	 * @param allRespList    buildTree()后的结果
	 * @param keys           部分节点转成平铺（父节点下的所有子节点需要平铺）
	 * @param listFlatResult 平铺的结果
	 */
	public static <T extends BaseTreeRecord<T>> void flatTree(List<T> allRespList, List<String> keys,
                                                              List<T> listFlatResult) {
		Map<String, List<T>> mapResps = allRespList.stream().collect(Collectors.groupingBy(p -> p.key()));
		for (String key : keys) {
			if (!CollectionUtils.isEmpty(mapResps.get(key))) {
				T testResp = mapResps.get(key).get(0);
				flatTree(testResp, listFlatResult);
				testResp.setChildren(new ArrayList<>());
				if(!CollectionUtils.isEmpty(testResp.listFullPath())) {
					testResp.listFullPath().clear();
				}
				listFlatResult.add(testResp);
			}

		}
	}

	/**
	 * 设置节点的全路径
	 * 
	 * @param <T>
	 * @param root
	 */
	public static <T extends BaseTreeRecord<T>> void setFullPath(T root) {
		if (!root.listFullPath().contains(root.key())) {
			root.addFullPath(root.key());
		}
		if(!CollectionUtils.isEmpty(root.getChildren())) {
			root.getChildren().forEach(item -> {
				root.listFullPath().forEach(fullPath -> {
					if (!item.listFullPath().contains(fullPath)) {
						item.addFullPath(fullPath);
					}
				});
				setFullPath(item);
			});
		}
	}
}
