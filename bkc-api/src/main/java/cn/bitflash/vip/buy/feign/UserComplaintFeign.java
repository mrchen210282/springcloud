package cn.bitflash.vip.buy.feign;

import cn.bitflash.entity.UserComplaintEntity;
import org.springframework.web.bind.annotation.PostMapping;

public interface UserComplaintFeign {

    @PostMapping("/inner/userComplaint/insert")
    void insert(UserComplaintEntity entity);

}
