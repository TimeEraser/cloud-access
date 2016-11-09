package zju.edu.als.dao;

import org.apache.ibatis.annotations.Param;
import zju.edu.als.domain.surgery.Surgery;

import java.util.List;

/**
 * Created by zzq on 2016/11/4.
 */
public interface SurgeryDao {

    public boolean insertSurgery(@Param("surgery")Surgery surgery);

    public boolean updateSurgery(@Param("surgery")Surgery surgery);

    public boolean endSurgery(@Param("surgeryNo")String surgeryNo);

    public List<Surgery> selectSurgeryByState(@Param("state")int state);

    public List<Surgery> selectAllSurgery();

    public Surgery selectSurgeryBySurgeryNo(@Param("surgeryNo")String surgeryNo);

    public List<Surgery> selectExecutingSurgeryBySurgeryNoList(@Param("surgeryNoList")List<String> surgeryNoList);


}
