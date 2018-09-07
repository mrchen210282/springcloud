package cn.bitflash.service.impl;

import cn.bitflash.entity.ComplaintUploadEntity;
import cn.bitflash.dao.ComplaintUploadDao;
import cn.bitflash.service.ComplaintUploadService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author wangjun
 * @date 2018年8月21日 下午4:48:48
 */
@Service("complaintUploadService")
public class ComplaintUploadServiceImpl extends ServiceImpl<ComplaintUploadDao, ComplaintUploadEntity> implements ComplaintUploadService {

}
