DROP TABLE IF EXISTS guardianData;
CREATE TABLE guardianData (
  `surgery_no` varchar(255) NOT NULL,
  `time_stamp` varchar(255) DEFAULT NULL,
  `heart_rate` varchar(255) DEFAULT NULL,
  `systolic_pressure` varchar(255) DEFAULT NULL,
  `diastolic_pressure` varchar(255) DEFAULT NULL,
  `blood_oxygen` varchar(255) DEFAULT NULL,
  `ecg_features` blob,
  PRIMARY KEY (`surgery_no`)
);

DROP TABLE IF EXISTS pressureData;
CREATE TABLE pressureData (
  `surgery_no` varchar(255) DEFAULT NULL COMMENT '手术号',
  `time_stamp` varchar(255) DEFAULT NULL COMMENT '时间',
  `in_blood_pressure` varchar(255) DEFAULT NULL COMMENT '采血压',
  `plasma_inlet_pressure` varchar(255) DEFAULT NULL COMMENT '血浆入口压P1st',
  `arterial_pressure` varchar(255) DEFAULT NULL COMMENT '动脉压',
  `venous_pressure` varchar(255) DEFAULT NULL COMMENT '静脉压',
  `plasma_pressure` varchar(255) DEFAULT NULL COMMENT '血浆压',
  `transmembrane_pressure` varchar(255) DEFAULT NULL COMMENT '跨膜压'
);

DROP TABLE IF EXISTS pumpSpeedData;
CREATE TABLE pumpSpeedData (
  `surgery_no` varchar(255) DEFAULT NULL,
  `time_stamp` varchar(255) DEFAULT NULL,
  `cumulative_time` varchar(255) DEFAULT NULL,
  `blood_pump` varchar(255) DEFAULT NULL,
  `separation_pump` varchar(255) DEFAULT NULL,
  `dialysis_pump` varchar(255) DEFAULT NULL,
  `tripe_pump` varchar(255) DEFAULT NULL,
  `filtration_pump` varchar(255) DEFAULT NULL,
  `circulating_pump` varchar(255) DEFAULT NULL,
  `heparin_pump` varchar(255) DEFAULT NULL,
  `warmer` varchar(255) DEFAULT NULL,
  `blood_pump_t` varchar(255) DEFAULT NULL COMMENT '血液泵累计',
  `separation_pump_t` varchar(255) DEFAULT NULL COMMENT 'FP累计',
  `dialysis_pump_t` varchar(255) DEFAULT NULL COMMENT 'DP累计',
  `tripe_pump_t` varchar(255) DEFAULT NULL COMMENT 'RP累计',
  `filtration_pump_t` varchar(255) DEFAULT NULL COMMENT 'FP2累计',
  `circulating_pump_t` varchar(255) DEFAULT NULL COMMENT 'CP累计',
  `heparin_pump_t` varchar(255) DEFAULT NULL COMMENT 'SP累计'
) ENGINE=InnoDB DEFAULT CHARSET=utf-8;