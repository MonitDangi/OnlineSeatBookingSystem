package com.mis.bookingrepositories;

import com.mis.bookingmodels.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface UserRepo extends JpaRepository<User,String> {
}
