//package cn.bitflash.service.impl;
//
//import cn.bitflash.entities.UserRelationEntity;
//import cn.bitflash.entity.UserRelationJoinAccountEntity;
//import cn.bitflash.dao.UserRelationDao;
//import cn.bitflash.service.UserRelationService;
//import com.baomidou.mybatisplus.service.impl.ServiceImpl;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author soso
// * @date 2018年5月21日 下午4:48:48
// */
//
//@Service("userRelationService")
//public class UserRelationServiceImpl extends ServiceImpl<UserRelationDao, UserRelationEntity>
//        implements UserRelationService {
//
//    @Override
//    public void insertTreeNode(String f_uid, String c_uid, String invitation_code) {
//
//        baseMapper.insertTreeNode(f_uid, c_uid, invitation_code);
//    }
//
//    @Override
//    public List<UserRelationJoinAccountEntity> selectTreeNodes(String f_uid) {
//        try {
//            return baseMapper.selectTreeNodes(f_uid);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//
//
//
//}
