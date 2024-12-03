package com.example.realestate.Service;

import com.example.realestate.Model.User;
import com.example.realestate.Model.User_Package;
import com.example.realestate.Repository.UserPackageRepository;
import com.example.realestate.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final UserRepository userRepository;
    private final UserPackageRepository userPackageRepository;

    // Chạy mỗi ngày để kiểm tra gói đăng ký hết hạn
    @Scheduled(cron = "0 0 0 * * ?") // Mỗi ngày lúc 0h
    public void checkExpiredSubscriptions() {
        List<User_Package> expiredPackages = userPackageRepository.findAllByEndDateBefore(LocalDateTime.now());

        for (User_Package userPackage : expiredPackages) {
            User user = userPackage.getUser();
            user.setCheckStatus(false); // Gói đã hết hạn, cập nhật lại trạng thái
            userRepository.save(user);
        }
    }
}
