#单继重 调整材料表材料名称长度
ALTER TABLE `t_material`
MODIFY COLUMN `material_name`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '材料名称' AFTER `dispatch_info_id`;