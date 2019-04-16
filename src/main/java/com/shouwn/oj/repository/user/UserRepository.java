package com.shouwn.oj.repository.user;

import com.shouwn.oj.model.user.UserRent;
import com.shouwn.oj.repository.problem.CommentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserRent, Long>, CommentRepositoryCustom {

}
