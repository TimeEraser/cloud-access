DROP TABLE IF EXISTS guardianData;
CREATE TABLE `guardianData` (
  `surgery_no` varchar(255) NOT NULL COMMENT '手术号',
  `time_stamp` varchar(255) NOT NULL COMMENT '时间戳',
  `heart_rate` int(11)  DEFAULT NULL COMMENT '心率',
  `systolic_pressure` int(11)  DEFAULT NULL COMMENT '收缩压',
  `diastolic_pressure` int(11)  DEFAULT NULL COMMENT '舒张压',
  `blood_oxygen` int(11)  DEFAULT NULL COMMENT '血氧',
  `ecg_features` blob,
  PRIMARY KEY (`surgery_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS pressureData;
CREATE TABLE `pressureData` (
  `surgery_no` varchar(255) NOT NULL COMMENT '手术号',
  `time_stamp` varchar(255) NOT NULL COMMENT '时间戳',
  `in_blood_pressure` INT DEFAULT NULL COMMENT '采血压',
  `plasma_inlet_pressure` INT DEFAULT NULL COMMENT '血浆入口压P1st',
  `arterial_pressure` INT DEFAULT NULL COMMENT '动脉压',
  `venous_pressure` INT DEFAULT NULL COMMENT '静脉压',
  `plasma_pressure` INT DEFAULT NULL COMMENT '血浆压',
  `transmembrane_pressure` INT DEFAULT NULL COMMENT '跨膜压',
  PRIMARY KEY (`surgery_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS pumpSpeedData;
CREATE TABLE `pumpSpeedData` (
  `surgery_no` varchar(255) NOT NULL COMMENT '手术号',
  `time_stamp` varchar(255) NOT NULL COMMENT '时间戳',
  `cumulative_time` varchar(255) DEFAULT NULL COMMENT '累计时间',
  `blood_pump` varchar(255) DEFAULT NULL COMMENT '血泵',
  `separation_pump` varchar(255) DEFAULT NULL COMMENT '分离泵',
  `dialysis_pump` varchar(255) DEFAULT NULL COMMENT '透析泵',
  `tripe_pump` varchar(255) DEFAULT NULL COMMENT '废液泵',
  `filtration_pump` varchar(255) DEFAULT NULL COMMENT '过滤泵',
  `circulating_pump` varchar(255) DEFAULT NULL COMMENT '累计泵',
  `heparin_pump` varchar(255) DEFAULT NULL COMMENT '肝素泵',
  `warmer` varchar(255) DEFAULT NULL COMMENT '加热温度',
  `blood_pump_t` varchar(255) DEFAULT NULL COMMENT '血液泵累计',
  `separation_pump_t` varchar(255) DEFAULT NULL COMMENT 'FP累计',
  `dialysis_pump_t` varchar(255) DEFAULT NULL COMMENT 'DP累计',
  `tripe_pump_t` varchar(255) DEFAULT NULL COMMENT 'RP累计',
  `filtration_pump_t` varchar(255) DEFAULT NULL COMMENT 'FP2累计',
  `circulating_pump_t` varchar(255) DEFAULT NULL COMMENT 'CP累计',
  `heparin_pump_t` varchar(255) DEFAULT NULL COMMENT 'SP累计',
  PRIMARY KEY (`surgery_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;