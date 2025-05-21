package com.mycom.myapp.repository;

import com.mycom.myapp.entity.Code;
import com.mycom.myapp.entity.key.CodeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommonCodeRepository extends JpaRepository<Code, CodeKey> {
    @Query("select c from Code c where c.id.groupCode in :groupCodes order by c.id.groupCode, c.orderNo")
    List<Code> findByGroupCodes(@Param("groupCodes") List<String> groupCodes);
}
