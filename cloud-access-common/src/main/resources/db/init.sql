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
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `surgeryNo` varchar(255) NOT NULL COMMENT '手术号',
  `timestamp` BIGINT NOT NULL COMMENT '时间戳',
  `cumulativeTime` BIGINT DEFAULT NULL COMMENT '累计时间',
  `bloodPump` INT DEFAULT NULL COMMENT '血泵',
  `separationPump` INT DEFAULT NULL COMMENT '分离泵',
  `dialysisPump` INT DEFAULT NULL COMMENT '透析泵',
  `tripePump` INT DEFAULT NULL COMMENT '废液泵',
  `filtrationPump` INT DEFAULT NULL COMMENT '过滤泵',
  `circulatingPump` INT DEFAULT NULL COMMENT '累计泵',
  `heparinPump` INT DEFAULT NULL COMMENT '肝素泵',
  `warmer`  DOUBLE NULL COMMENT '加热温度',
  `bloodPumpT` INT DEFAULT NULL COMMENT '血液泵累计',
  `separationPumpT` INT DEFAULT NULL COMMENT 'FP累计',
  `dialysisPumpT` INT DEFAULT NULL COMMENT 'DP累计',
  `tripePumpT` INT DEFAULT NULL COMMENT 'RP累计',
  `filtrationPumpT` INT DEFAULT NULL COMMENT 'FP2累计',
  `circulatingPumpT` INT DEFAULT NULL COMMENT 'CP累计',
  `heparinPumpT` INT DEFAULT NULL COMMENT 'SP累计',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS alarmSettingData;
CREATE TABLE `alarmSettingData`(
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `alarmItem` varchar(255) NOT NULL COMMENT '报警项目',
  `ceiling` DOUBLE NOT NULL COMMENT '阈值上限',
  `floor` DOUBLE NOT NULL COMMENT '阈值下限',
  `note` varchar(255) NOT NULL COMMENT '特定值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO alarmSettingData(alarmItem, ceiling, floor, note) VALUES
  ('heartRate',100,50,''),
  ('systolicPressure',180,80,''),
  ('diastolicPressure',100,50,''),
  ('bloodOxygen',20,1,''),
  ('inBloodPressure',100,50,''),
  ('plasmaInletPressure',100,50,''),
  ('arterialPressure',100,50,''),
  ('venousPressure',100,50,''),
  ('transmembranePressure',100,50,''),
  ('bloodPump',100,50,''),
  ('separationPump',100,50,''),
  ('dialysisPump',100,50,''),
  ('tripePump',100,50,''),
  ('filtrationPump',100,50,''),
  ('circulatingPump',100,50,''),
  ('warmer',100,50,'')
DROP TABLE IF EXISTS surgery;
CREATE TABLE `surgery`(
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
  `surgeryNo` varchar(255) NOT NULL COMMENT '手术号',
  `patient` varchar(255)  COMMENT '病人',
  `patientId` varchar(255)  COMMENT '病人身份证号',
  `doctor` varchar(255) COMMENT '主治医生',
  `doctorId` VARCHAR(255)  COMMENT '主治医生Id',
  `description` VARCHAR(255)  COMMENT '病情描述',
  `state` INT NOT NULL DEFAULT 0 COMMENT '手术状态',
  `alarmPerson` varchar(255) NOT NULL COMMENT '报警人',
  `lastAlarm` BIGINT NOT NULL DEFAULT 0 COMMENT '上次报警时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`surgeryNo`)
);