package com.mdoldur.usertask.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mdoldur.usertask.entity.GnlStEntity;

@Repository
public interface GnlStRepository extends JpaRepository<GnlStEntity, Long> {

	GnlStEntity findByEntCodeNameAndShrtCode(String entCodeName, String shrtCode);

}
