package zju.edu.als.dao;

import org.apache.ibatis.annotations.Param;
import zju.edu.als.domain.surgery.Surgery;

/**
 * Created by zzq on 2016/11/4.
 */
public interface SurgeryDao {

    public boolean insertOrUpdateSurgery(@Param("surgery")Surgery surgery);

    public Surgery selectSurgery(@Param("surgeryNo")String surgeryNo);
}
