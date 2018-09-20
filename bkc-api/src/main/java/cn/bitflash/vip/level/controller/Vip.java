package cn.bitflash.vip.level.controller;

import cn.bitflash.annotation.Login;
import cn.bitflash.entity.*;
import cn.bitflash.exception.RRException;
import cn.bitflash.util.Assert;
import cn.bitflash.util.CodeUtils;
import cn.bitflash.util.Common;
import cn.bitflash.util.R;
import cn.bitflash.vip.level.entity.UserRelationJoinAccountEntity;
import cn.bitflash.vip.level.feign.LevelFeign;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/level")
@Api(value = "加入社区Con", tags = {"加入"})
public class Vip {

    @Autowired
    private LevelFeign levelFeign;

    @Login
    @PostMapping("updateLevel")
    public R updateVipLevel(@RequestAttribute("uid") String uid) {

        UserInfoEntity userInfo = levelFeign.selectUserInfoByUid(uid);
        UserAccountEntity userAccount = levelFeign.selectAccountByUid(uid);
        List<UserVipConditionsEntity> vipConditions = levelFeign.selectVipConditonsByLevel(userInfo.getIsVip());
        if (vipConditions.size() < 2) {
            return R.error("更高算力暂未开放");
        }
        BigDecimal purchase = userAccount.getPurchase();
        if (purchase.doubleValue() < vipConditions.get(1).getBkcount()) {
            return R.error("bkc数量不够");
        }
        /**
         * 扣除冻结的bkc
         * 提升vip  userinfo
         */
        





        List<UserRelationJoinAccountEntity> child_user = levelFeign.selectTreeNodes(uid);
        if (child_user != null || child_user.size() != 0) {
            return R.ok();
        }
        String code[] = userInfo.getInvitationCode().split("-");
        String invitCode = code[0];
        String area = code[1];
        UserInvitationCodeEntity pCode = levelFeign.selectInvitationCodeByCode(invitCode);
        List<UserRelationJoinAccountEntity> f_user = levelFeign.selectTreeNodes(pCode.getUid());
        //父类下所有的子类数量（包含父类）
        int size = f_user.size();
        switch (area) {
            case "l":
                if (size == 1) {
                    levelFeign.insertTreeNode(pCode.getUid(), uid, invitCode);
                } else if (size == 2) {
                    levelFeign.insertTreeNode(f_user.get(1).getUid(), uid, invitCode);
                } else if (size > 2) {
                    //筛选出左区第一个子节点
                    UserRelationJoinAccountEntity ue = f_user.stream().filter((u) -> u.getLft() == f_user.get(0).getLft() + 1).findFirst().get();
                    List<UserRelationJoinAccountEntity> child2_user = f_user.stream().filter((u) ->
                            u.getLft() >= ue.getLft() && u.getRgt() <= ue.getRgt()).collect(Collectors.toList());
                    if (child2_user.size() == 1) {
                        levelFeign.insertTreeNode(child2_user.get(0).getUid(), uid, invitCode);
                    } else if (child2_user.size() > 1) {
                        levelFeign.insertTreeNode(this.getChildNode(child2_user, new HashMap<>()), uid, invitCode);
                    }
                }
                break;
            case "c":
                if (size == 1) {
                    //等于1 = 没有左区，需要先排左区
                    throw new RRException("邀请码不正确");
                }
                //等于2代表直接父类下面开辟中区,或者左区下面只有一个点
                else if (size == 2) {
                    levelFeign.insertTreeNode(pCode.getUid(), uid, invitCode);
                } else if (size > 3) {
                    if (f_user.get(0).getRgt() == f_user.get(1).getRgt() + 1) {
                        //   o 情况1   实现 o
                        //  o             o o
                        // o             o
                        levelFeign.insertTreeNode(pCode.getUid(), uid, invitCode);
                        return R.ok();
                    }
                    //筛选出右区第一个子节点
                    UserRelationJoinAccountEntity ue = f_user.stream().filter((u) -> u.getLft() == f_user.get(1).getRgt() + 1).findFirst().get();
                    List<UserRelationJoinAccountEntity> child2_user = f_user.stream().filter((u) ->
                            u.getLft() >= ue.getLft() && u.getRgt() <= ue.getRgt()).collect(Collectors.toList());
                    if (child2_user.size() == 1) {
                        //    o  情况2  实现 o
                        //   o o           o  o
                        //                   o
                        levelFeign.insertTreeNode(child2_user.get(0).getUid(), uid, invitCode);
                    } else {
                        levelFeign.insertTreeNode(this.getChildNode(child2_user, new HashMap<>()), uid, invitCode);
                    }
                }
                break;
            case "r":
                if (size < 3 || f_user.get(0).getRgt() == f_user.get(1).getRgt() + 1) {
                    //等于2 = 没有左区，中区，需要先排左区和中区
                    throw new RRException("邀请码不正确");
                } else if (size == 3 && f_user.get(0).getRgt() != f_user.get(1).getRgt() + 1) {
                    levelFeign.insertTreeNode(pCode.getUid(), uid, invitCode);
                } else if (size > 3) {

                    UserRelationJoinAccountEntity ue = f_user.stream().filter(u -> f_user.get(1).getRgt() + 1 == u.getLft()).findFirst().get();
                    UserRelationJoinAccountEntity ue2 = f_user.stream().filter(u -> f_user.get(0).getRgt() == u.getRgt() + 1).findFirst().get();
                    if (ue.equals(ue2)) {
                        //    o     情况：不存在右区  实现   o
                        //  o  o                        o  o  o
                        // o                          o
                        levelFeign.insertTreeNode(f_user.get(0).getUid(), uid, invitCode);
                    } else {
                        //     o     情况：不存在右区  实现     o
                        //  o  o  o                       o  o  o
                        // o                                   o
                        List<UserRelationJoinAccountEntity> child2_user = f_user.stream().filter((u) ->
                                u.getLft() >= ue2.getLft() && u.getRgt() <= ue2.getRgt()).collect(Collectors.toList());
                        levelFeign.insertTreeNode(this.getChildNode(child2_user, new HashMap<>()), uid, invitCode);
                    }
                }

        }

    }


    public String getChildNode(List<UserRelationJoinAccountEntity> p1_user, Map<String, UserRelationJoinAccountEntity> map) {
        /**
         * 寻找收益最高的下面的点
         * 实现原理：递归筛选，直到筛选出末尾节点
         */
        UserRelationJoinAccountEntity last_node = null;
        if (p1_user.size() > 1) {
            //左区比右区收益高/相等 添加到左区
            UserRelationJoinAccountEntity p2_user;
            //左节点
            p2_user = p1_user.stream().filter((u) -> u.getLft() == p1_user.get(0).getLft() + 1).findFirst().get();
            List<UserRelationJoinAccountEntity> p3_user = p1_user.stream().filter((u) -> (
                    u.getLft() >= p2_user.getLft()) && u.getRgt() <= p2_user.getRgt()).collect(Collectors.toList());
            if (p3_user.size() == 1) {
                map.put("key", p3_user.get(0));
            } else {
                this.getChildNode(p3_user, map);
            }
        }
        last_node = map.get("key");
        return last_node.getUid();
    }
}
