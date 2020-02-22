-- ----------------------------
-- Records of app_role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'Standard User - Has no admin rights', 'STANDARD_USER');
INSERT INTO `role` VALUES (2, 'Admin User - Has permission to perform admin tasks', 'ADMIN');

INSERT INTO `user`(`id`, `account_non_expired`, `account_non_locked`, `address`, `credential_non_expired`, `date_created`, `email`, `first_name`, `is_enabled`, `last_name`, `password`, `phone`, `username`) VALUES (1, b'1', b'1', '1', b'1', '2020-02-21 19:44:35', '1', '1', b'1', '1', '$2a$10$NH.KCLCxVZi.vSkjOGvN5enB15KzSXP0AUbIlYLtOzqMUeM5slJK2', '1', 'admin');

INSERT INTO `user_roles` VALUES (1, 2, 1);
