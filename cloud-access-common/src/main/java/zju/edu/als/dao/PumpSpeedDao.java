package zju.edu.als.dao;

import org.apache.ibatis.annotations.Param;
import zju.edu.als.domain.PumpSpeedData;

import java.util.List;

/**
 * Created by zzq on 2016/10/27.
 */
public interface PumpSpeedDao {
    boolean insertPumpSpeedData(@Param("pumpSpeedData") PumpSpeedData pumpSpeedData);

    boolean batchInsertPumpSpeedData(@Param("pumpSpeedDataList") List<PumpSpeedData> pumpSpeedDataList);

    List<PumpSpeedData> selectPumpSpeedDataBySurgeryNo(@Param("surgeryNo") String surgeryNo);

    List<PumpSpeedData> selectPumpSpeedDataBySurgeryNoWithTimeRange(@Param("surgeryNo") String surgeryNo,@Param("beginTime") long beginTime,@Param("endTime") long endTime);
}
