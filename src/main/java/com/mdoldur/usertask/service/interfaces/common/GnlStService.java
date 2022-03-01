package com.mdoldur.usertask.service.interfaces.common;

import com.mdoldur.usertask.entity.GnlStEntity;

public interface GnlStService {
	
	GnlStEntity findByEntCodeNameAndShrtCode(String entCodeName, String shrtCode);

}
