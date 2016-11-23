DROP TABLE IF EXISTS alarmSetting;
CREATE TABLE `alarmSetting`(
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `alarmItem` varchar(255) NOT NULL COMMENT '报警项目',
  `ceiling` DOUBLE NOT NULL COMMENT '阈值上限',
  `floor` DOUBLE NOT NULL COMMENT '阈值下限',
  `note` varchar(255) NOT NULL COMMENT '特定值',
  `type` varchar(255) NOT NULL COMMENT '类别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO alsCloud.alarmSetting (alarmItem, ceiling, floor, note, type) VALUES ('heartRate', 100, 50, '心率', 0);
INSERT INTO alsCloud.alarmSetting (alarmItem, ceiling, floor, note, type) VALUES ('systolicPressure', 180, 80, '收缩压', 0);
INSERT INTO alsCloud.alarmSetting (alarmItem, ceiling, floor, note, type) VALUES ('diastolicPressure', 100, 50, '舒张压', 0);
INSERT INTO alsCloud.alarmSetting (alarmItem, ceiling, floor, note, type) VALUES ('bloodOxygen', 20, 1, '血氧', 0);
INSERT INTO alsCloud.alarmSetting (alarmItem, ceiling, floor, note, type) VALUES ('inBloodPressure', 100, 50, '采血压', 1);
INSERT INTO alsCloud.alarmSetting (alarmItem, ceiling, floor, note, type) VALUES ('plasmaInletPressure', 100, 50, '血浆入口压', 1);
INSERT INTO alsCloud.alarmSetting (alarmItem, ceiling, floor, note, type) VALUES ('arterialPressure', 100, 50, '动脉压', 1);
INSERT INTO alsCloud.alarmSetting (alarmItem, ceiling, floor, note, type) VALUES ('venousPressure', 100, 50, '静脉压', 1);
INSERT INTO alsCloud.alarmSetting (alarmItem, ceiling, floor, note, type) VALUES ('transmembranePressure', 100, 50, '跨膜压', 1);
INSERT INTO alsCloud.alarmSetting (alarmItem, ceiling, floor, note, type) VALUES ('bloodPump', 100, 50, '血泵', 2);
INSERT INTO alsCloud.alarmSetting (alarmItem, ceiling, floor, note, type) VALUES ('separationPump', 100, 50, '分离泵', 2);
INSERT INTO alsCloud.alarmSetting (alarmItem, ceiling, floor, note, type) VALUES ('dialysisPump', 100, 50, '透析泵', 2);
INSERT INTO alsCloud.alarmSetting (alarmItem, ceiling, floor, note, type) VALUES ('tripePump', 100, 50, '废液泵', 2);
INSERT INTO alsCloud.alarmSetting (alarmItem, ceiling, floor, note, type) VALUES ('filtrationPump', 100, 50, '过滤泵', 2);
INSERT INTO alsCloud.alarmSetting (alarmItem, ceiling, floor, note, type) VALUES ('circulatingPump', 100, 50, '累计泵', 2);
INSERT INTO alsCloud.alarmSetting (alarmItem, ceiling, floor, note, type) VALUES ('heparinPump',100,50,'肝素泵',2);
INSERT INTO alsCloud.alarmSetting (alarmItem, ceiling, floor, note, type) VALUES ('warmer', 100, 50, '加热温度', 2);
