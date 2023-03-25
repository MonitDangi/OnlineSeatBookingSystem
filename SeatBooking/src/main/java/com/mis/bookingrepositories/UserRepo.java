package com.mis.bookingrepositories;

import com.mis.bookingmodels.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,String> {

    @Modifying
    @Transactional
    @Query(value = "update User u set u.password=?1 where u.userId=?2")
    void updatepassword(String password, String userId);
}
