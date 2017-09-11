package com.tc.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.tc.greendao.Criminal;
import com.tc.greendao.Opinion;
import com.tc.greendao.TraceEvidence;
import com.tc.greendao.User;

import com.tc.greendao.CriminalDao;
import com.tc.greendao.OpinionDao;
import com.tc.greendao.TraceEvidenceDao;
import com.tc.greendao.UserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig criminalDaoConfig;
    private final DaoConfig opinionDaoConfig;
    private final DaoConfig traceEvidenceDaoConfig;
    private final DaoConfig userDaoConfig;

    private final CriminalDao criminalDao;
    private final OpinionDao opinionDao;
    private final TraceEvidenceDao traceEvidenceDao;
    private final UserDao userDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        criminalDaoConfig = daoConfigMap.get(CriminalDao.class).clone();
        criminalDaoConfig.initIdentityScope(type);

        opinionDaoConfig = daoConfigMap.get(OpinionDao.class).clone();
        opinionDaoConfig.initIdentityScope(type);

        traceEvidenceDaoConfig = daoConfigMap.get(TraceEvidenceDao.class).clone();
        traceEvidenceDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        criminalDao = new CriminalDao(criminalDaoConfig, this);
        opinionDao = new OpinionDao(opinionDaoConfig, this);
        traceEvidenceDao = new TraceEvidenceDao(traceEvidenceDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);

        registerDao(Criminal.class, criminalDao);
        registerDao(Opinion.class, opinionDao);
        registerDao(TraceEvidence.class, traceEvidenceDao);
        registerDao(User.class, userDao);
    }
    
    public void clear() {
        criminalDaoConfig.clearIdentityScope();
        opinionDaoConfig.clearIdentityScope();
        traceEvidenceDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
    }

    public CriminalDao getCriminalDao() {
        return criminalDao;
    }

    public OpinionDao getOpinionDao() {
        return opinionDao;
    }

    public TraceEvidenceDao getTraceEvidenceDao() {
        return traceEvidenceDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

}