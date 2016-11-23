DROP TABLE IF EXISTS guardianData;
CREATE TABLE `guardianData` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `surgeryNo` varchar(255) NOT NULL COMMENT '手术号',
  `timestamp` BIGINT NOT NULL COMMENT '时间戳',
  `heartRate` int(11)  DEFAULT NULL COMMENT '心率',
  `systolicPressure` int(11)  DEFAULT NULL COMMENT '收缩压',
  `diastolicPressure` int(11)  DEFAULT NULL COMMENT '舒张压',
  `bloodOxygen` int(11)  DEFAULT NULL COMMENT '血氧',
  `ecgFeatures` blob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS pressureData;
CREATE TABLE `pressureData` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `surgeryNo` varchar(255) NOT NULL COMMENT '手术号',
  `timestamp` BIGINT NOT NULL COMMENT '时间戳',
  `inBloodPressure` INT DEFAULT NULL COMMENT '采血压',
  `plasmaInletPressure` INT DEFAULT NULL COMMENT '血浆入口压P1st',
  `arterialPressure` INT DEFAULT NULL COMMENT '动脉压',
  `venousPressure` INT DEFAULT NULL COMMENT '静脉压',
  `plasmaPressure` INT DEFAULT NULL COMMENT '血浆压',
  `transmembranePressure` INT DEFAULT NULL COMMENT '跨膜压',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS pumpSpeedData;
CREATE TABLE `pumpSpeedData` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `surgeryNo` varchar(255) NOT NULL COMMENT '手术号',
  `timestamp` bigint(20) NOT NULL COMMENT '时间戳',
  `cumulativeTime` bigint(20) DEFAULT NULL COMMENT '累计时间',
  `bloodPump` int(11) DEFAULT NULL COMMENT '血泵',
  `separationPump` int(11) DEFAULT NULL COMMENT '分离泵',
  `dialysisPump` int(11) DEFAULT NULL COMMENT '透析泵',
  `tripePump` int(11) DEFAULT NULL COMMENT '废液泵',
  `filtrationPump` int(11) DEFAULT NULL COMMENT '过滤泵',
  `circulatingPump` int(11) DEFAULT NULL COMMENT '累计泵',
  `heparinPump` int(11) DEFAULT NULL COMMENT '肝素泵',
  `warmer` double DEFAULT NULL COMMENT '加热温度',
  `bloodPumpT` int(11) DEFAULT NULL COMMENT '血液泵累计',
  `separationPumpT` int(11) DEFAULT NULL COMMENT 'FP累计',
  `dialysisPumpT` int(11) DEFAULT NULL COMMENT 'DP累计',
  `tripePumpT` int(11) DEFAULT NULL COMMENT 'RP累计',
  `filtrationPumpT` int(11) DEFAULT NULL COMMENT 'FP2累计',
  `circulatingPumpT` int(11) DEFAULT NULL COMMENT 'CP累计',
  `heparinPumpT` int(11) DEFAULT NULL COMMENT 'SP累计',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8


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

DROP TABLE IF EXISTS surgery;
CREATE TABLE `surgery` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `surgeryNo` varchar(255) NOT NULL COMMENT '手术号',
  `startTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '开始时间',
  `endTime` bigint(20) NOT NULL DEFAULT '0' COMMENT '结束时间',
  `patient` varchar(255) DEFAULT NULL COMMENT '病人',
  `patientId` varchar(255) DEFAULT NULL COMMENT '病人身份证号',
  `doctor` varchar(255) DEFAULT NULL COMMENT '主治医生',
  `doctorId` varchar(255) DEFAULT NULL COMMENT '主治医生Id',
  `description` varchar(255) DEFAULT NULL COMMENT '病情描述',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '手术状态',
  `alarmPerson` varchar(255) NOT NULL COMMENT '报警人',
  `lastAlarm` bigint(20) NOT NULL DEFAULT '0' COMMENT '上次报警时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `surgeryNo` (`surgeryNo`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8