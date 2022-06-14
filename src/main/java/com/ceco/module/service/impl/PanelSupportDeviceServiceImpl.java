package com.ccj.module.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccj.channel.admin.model.resp.DeviceModelResp;
import com.ccj.module.dao.PanelSupportDeviceMapper;
import com.ccj.module.entity.PanelSupportDevice;
import com.ccj.module.service.IPanelSupportDeviceService;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <p>
 * 固件支持设备型号 服务实现类
 * </p>
 *
 * @author zmj
 * @since 2021-10-13
 */
@Service
public class PanelSupportDeviceServiceImpl extends ServiceImpl<PanelSupportDeviceMapper, PanelSupportDevice> implements IPanelSupportDeviceService {

    @Override
    public List<DeviceModelResp> selectSupportDeviceList(List<String> panelIdList) {
        return this.baseMapper.selectSupportDeviceList(panelIdList);
    }
}
