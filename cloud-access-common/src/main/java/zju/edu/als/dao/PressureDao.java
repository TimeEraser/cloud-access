package zju.edu.als.dao;

import org.apache.ibatis.annotations.Param;
import zju.edu.als.domain.data.PressureData;

import java.util.List;

/**
 * Created by zzq on 2016/10/27.
 */
public interface PressureDao {
    boolean insertPressureData(@Param("pressureData") PressureData pressureData);

    boolean batchInsertPressureData(@Param("pressureDataList") List<PressureData> pressureDataList);

    List<PressureData> selectPressureDataBySurgeryNo(@Param("surgeryNo") String surgeryNo);

    List<PressureData> selectPressureDataBySurgeryNoWithTimeRange(@Param("surgeryNo") String surgeryNo,@Param("beginTime") long beginTime,@Param("endTime") long endTime);
}
