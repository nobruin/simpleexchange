package com.jaya.simpleexchange.repository

import org.apache.catalina.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long>