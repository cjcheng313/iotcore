package com.ccj.module.service.impl;

import com.ccj.module.entity.FirmwareVersion;
import com.ccj.module.dao.FirmwareVersionMapper;
import com.ccj.module.service.IFirmwareVersionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 固件版本 服务实现类
 * </p>
 *
 * @author zmj
 * @since 2021-10-13
 */
@Service
public class FirmwareVersionServiceImpl extends ServiceImpl<FirmwareVersionMapper, FirmwareVersion> implements IFirmwareVersionService {

}
