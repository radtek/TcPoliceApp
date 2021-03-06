package com.tc.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CRIMINAL".
*/
public class CriminalDao extends AbstractDao<Criminal, Long> {

    public static final String TABLENAME = "CRIMINAL";

    /**
     * Properties of entity Criminal.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property HandleNumber = new Property(1, String.class, "handleNumber", false, "HANDLE_NUMBER");
        public final static Property Number = new Property(2, String.class, "number", false, "NUMBER");
        public final static Property Type = new Property(3, String.class, "type", false, "TYPE");
        public final static Property Area = new Property(4, String.class, "area", false, "AREA");
        public final static Property IsLife = new Property(5, int.class, "isLife", false, "IS_LIFE");
        public final static Property IsCrime = new Property(6, int.class, "isCrime", false, "IS_CRIME");
        public final static Property StartTime = new Property(7, String.class, "startTime", false, "START_TIME");
        public final static Property EndTime = new Property(8, String.class, "endTime", false, "END_TIME");
        public final static Property Place = new Property(9, String.class, "place", false, "PLACE");
        public final static Property SolveTime = new Property(10, String.class, "solveTime", false, "SOLVE_TIME");
        public final static Property InquestPlace = new Property(11, String.class, "inquestPlace", false, "INQUEST_PLACE");
        public final static Property InquestReason = new Property(12, String.class, "inquestReason", false, "INQUEST_REASON");
        public final static Property CaseProcess = new Property(13, String.class, "caseProcess", false, "CASE_PROCESS");
        public final static Property ProtectorName = new Property(14, String.class, "protectorName", false, "PROTECTOR_NAME");
        public final static Property ProtectorComany = new Property(15, String.class, "protectorComany", false, "PROTECTOR_COMANY");
        public final static Property ProtectMeasures = new Property(16, String.class, "protectMeasures", false, "PROTECT_MEASURES");
        public final static Property ProtectTime = new Property(17, String.class, "protectTime", false, "PROTECT_TIME");
        public final static Property SpotCondition = new Property(18, String.class, "spotCondition", false, "SPOT_CONDITION");
        public final static Property WeatherCondition = new Property(19, String.class, "weatherCondition", false, "WEATHER_CONDITION");
        public final static Property LightCondition = new Property(20, String.class, "lightCondition", false, "LIGHT_CONDITION");
        public final static Property SpotConduct = new Property(21, String.class, "spotConduct", false, "SPOT_CONDUCT");
        public final static Property InquesterName = new Property(22, String.class, "inquesterName", false, "INQUESTER_NAME");
        public final static Property SpotPeople = new Property(23, String.class, "spotPeople", false, "SPOT_PEOPLE");
        public final static Property SpotLeft = new Property(24, String.class, "spotLeft", false, "SPOT_LEFT");
        public final static Property InquestCondition = new Property(25, String.class, "inquestCondition", false, "INQUEST_CONDITION");
        public final static Property VictimName = new Property(26, String.class, "victimName", false, "VICTIM_NAME");
        public final static Property LossGoods = new Property(27, String.class, "lossGoods", false, "LOSS_GOODS");
        public final static Property RecordTime = new Property(28, String.class, "recordTime", false, "RECORD_TIME");
        public final static Property Injury = new Property(29, String.class, "injury", false, "INJURY");
        public final static Property Witness = new Property(30, String.class, "witness", false, "WITNESS");
    }


    public CriminalDao(DaoConfig config) {
        super(config);
    }
    
    public CriminalDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CRIMINAL\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"HANDLE_NUMBER\" TEXT," + // 1: handleNumber
                "\"NUMBER\" TEXT," + // 2: number
                "\"TYPE\" TEXT," + // 3: type
                "\"AREA\" TEXT," + // 4: area
                "\"IS_LIFE\" INTEGER NOT NULL ," + // 5: isLife
                "\"IS_CRIME\" INTEGER NOT NULL ," + // 6: isCrime
                "\"START_TIME\" TEXT," + // 7: startTime
                "\"END_TIME\" TEXT," + // 8: endTime
                "\"PLACE\" TEXT," + // 9: place
                "\"SOLVE_TIME\" TEXT," + // 10: solveTime
                "\"INQUEST_PLACE\" TEXT," + // 11: inquestPlace
                "\"INQUEST_REASON\" TEXT," + // 12: inquestReason
                "\"CASE_PROCESS\" TEXT," + // 13: caseProcess
                "\"PROTECTOR_NAME\" TEXT," + // 14: protectorName
                "\"PROTECTOR_COMANY\" TEXT," + // 15: protectorComany
                "\"PROTECT_MEASURES\" TEXT," + // 16: protectMeasures
                "\"PROTECT_TIME\" TEXT," + // 17: protectTime
                "\"SPOT_CONDITION\" TEXT," + // 18: spotCondition
                "\"WEATHER_CONDITION\" TEXT," + // 19: weatherCondition
                "\"LIGHT_CONDITION\" TEXT," + // 20: lightCondition
                "\"SPOT_CONDUCT\" TEXT," + // 21: spotConduct
                "\"INQUESTER_NAME\" TEXT," + // 22: inquesterName
                "\"SPOT_PEOPLE\" TEXT," + // 23: spotPeople
                "\"SPOT_LEFT\" TEXT," + // 24: spotLeft
                "\"INQUEST_CONDITION\" TEXT," + // 25: inquestCondition
                "\"VICTIM_NAME\" TEXT," + // 26: victimName
                "\"LOSS_GOODS\" TEXT," + // 27: lossGoods
                "\"RECORD_TIME\" TEXT," + // 28: recordTime
                "\"INJURY\" TEXT," + // 29: injury
                "\"WITNESS\" TEXT);"); // 30: witness
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CRIMINAL\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Criminal entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String handleNumber = entity.getHandleNumber();
        if (handleNumber != null) {
            stmt.bindString(2, handleNumber);
        }
 
        String number = entity.getNumber();
        if (number != null) {
            stmt.bindString(3, number);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(4, type);
        }
 
        String area = entity.getArea();
        if (area != null) {
            stmt.bindString(5, area);
        }
        stmt.bindLong(6, entity.getIsLife());
        stmt.bindLong(7, entity.getIsCrime());
 
        String startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindString(8, startTime);
        }
 
        String endTime = entity.getEndTime();
        if (endTime != null) {
            stmt.bindString(9, endTime);
        }
 
        String place = entity.getPlace();
        if (place != null) {
            stmt.bindString(10, place);
        }
 
        String solveTime = entity.getSolveTime();
        if (solveTime != null) {
            stmt.bindString(11, solveTime);
        }
 
        String inquestPlace = entity.getInquestPlace();
        if (inquestPlace != null) {
            stmt.bindString(12, inquestPlace);
        }
 
        String inquestReason = entity.getInquestReason();
        if (inquestReason != null) {
            stmt.bindString(13, inquestReason);
        }
 
        String caseProcess = entity.getCaseProcess();
        if (caseProcess != null) {
            stmt.bindString(14, caseProcess);
        }
 
        String protectorName = entity.getProtectorName();
        if (protectorName != null) {
            stmt.bindString(15, protectorName);
        }
 
        String protectorComany = entity.getProtectorComany();
        if (protectorComany != null) {
            stmt.bindString(16, protectorComany);
        }
 
        String protectMeasures = entity.getProtectMeasures();
        if (protectMeasures != null) {
            stmt.bindString(17, protectMeasures);
        }
 
        String protectTime = entity.getProtectTime();
        if (protectTime != null) {
            stmt.bindString(18, protectTime);
        }
 
        String spotCondition = entity.getSpotCondition();
        if (spotCondition != null) {
            stmt.bindString(19, spotCondition);
        }
 
        String weatherCondition = entity.getWeatherCondition();
        if (weatherCondition != null) {
            stmt.bindString(20, weatherCondition);
        }
 
        String lightCondition = entity.getLightCondition();
        if (lightCondition != null) {
            stmt.bindString(21, lightCondition);
        }
 
        String spotConduct = entity.getSpotConduct();
        if (spotConduct != null) {
            stmt.bindString(22, spotConduct);
        }
 
        String inquesterName = entity.getInquesterName();
        if (inquesterName != null) {
            stmt.bindString(23, inquesterName);
        }
 
        String spotPeople = entity.getSpotPeople();
        if (spotPeople != null) {
            stmt.bindString(24, spotPeople);
        }
 
        String spotLeft = entity.getSpotLeft();
        if (spotLeft != null) {
            stmt.bindString(25, spotLeft);
        }
 
        String inquestCondition = entity.getInquestCondition();
        if (inquestCondition != null) {
            stmt.bindString(26, inquestCondition);
        }
 
        String victimName = entity.getVictimName();
        if (victimName != null) {
            stmt.bindString(27, victimName);
        }
 
        String lossGoods = entity.getLossGoods();
        if (lossGoods != null) {
            stmt.bindString(28, lossGoods);
        }
 
        String recordTime = entity.getRecordTime();
        if (recordTime != null) {
            stmt.bindString(29, recordTime);
        }
 
        String injury = entity.getInjury();
        if (injury != null) {
            stmt.bindString(30, injury);
        }
 
        String witness = entity.getWitness();
        if (witness != null) {
            stmt.bindString(31, witness);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Criminal entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String handleNumber = entity.getHandleNumber();
        if (handleNumber != null) {
            stmt.bindString(2, handleNumber);
        }
 
        String number = entity.getNumber();
        if (number != null) {
            stmt.bindString(3, number);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(4, type);
        }
 
        String area = entity.getArea();
        if (area != null) {
            stmt.bindString(5, area);
        }
        stmt.bindLong(6, entity.getIsLife());
        stmt.bindLong(7, entity.getIsCrime());
 
        String startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindString(8, startTime);
        }
 
        String endTime = entity.getEndTime();
        if (endTime != null) {
            stmt.bindString(9, endTime);
        }
 
        String place = entity.getPlace();
        if (place != null) {
            stmt.bindString(10, place);
        }
 
        String solveTime = entity.getSolveTime();
        if (solveTime != null) {
            stmt.bindString(11, solveTime);
        }
 
        String inquestPlace = entity.getInquestPlace();
        if (inquestPlace != null) {
            stmt.bindString(12, inquestPlace);
        }
 
        String inquestReason = entity.getInquestReason();
        if (inquestReason != null) {
            stmt.bindString(13, inquestReason);
        }
 
        String caseProcess = entity.getCaseProcess();
        if (caseProcess != null) {
            stmt.bindString(14, caseProcess);
        }
 
        String protectorName = entity.getProtectorName();
        if (protectorName != null) {
            stmt.bindString(15, protectorName);
        }
 
        String protectorComany = entity.getProtectorComany();
        if (protectorComany != null) {
            stmt.bindString(16, protectorComany);
        }
 
        String protectMeasures = entity.getProtectMeasures();
        if (protectMeasures != null) {
            stmt.bindString(17, protectMeasures);
        }
 
        String protectTime = entity.getProtectTime();
        if (protectTime != null) {
            stmt.bindString(18, protectTime);
        }
 
        String spotCondition = entity.getSpotCondition();
        if (spotCondition != null) {
            stmt.bindString(19, spotCondition);
        }
 
        String weatherCondition = entity.getWeatherCondition();
        if (weatherCondition != null) {
            stmt.bindString(20, weatherCondition);
        }
 
        String lightCondition = entity.getLightCondition();
        if (lightCondition != null) {
            stmt.bindString(21, lightCondition);
        }
 
        String spotConduct = entity.getSpotConduct();
        if (spotConduct != null) {
            stmt.bindString(22, spotConduct);
        }
 
        String inquesterName = entity.getInquesterName();
        if (inquesterName != null) {
            stmt.bindString(23, inquesterName);
        }
 
        String spotPeople = entity.getSpotPeople();
        if (spotPeople != null) {
            stmt.bindString(24, spotPeople);
        }
 
        String spotLeft = entity.getSpotLeft();
        if (spotLeft != null) {
            stmt.bindString(25, spotLeft);
        }
 
        String inquestCondition = entity.getInquestCondition();
        if (inquestCondition != null) {
            stmt.bindString(26, inquestCondition);
        }
 
        String victimName = entity.getVictimName();
        if (victimName != null) {
            stmt.bindString(27, victimName);
        }
 
        String lossGoods = entity.getLossGoods();
        if (lossGoods != null) {
            stmt.bindString(28, lossGoods);
        }
 
        String recordTime = entity.getRecordTime();
        if (recordTime != null) {
            stmt.bindString(29, recordTime);
        }
 
        String injury = entity.getInjury();
        if (injury != null) {
            stmt.bindString(30, injury);
        }
 
        String witness = entity.getWitness();
        if (witness != null) {
            stmt.bindString(31, witness);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Criminal readEntity(Cursor cursor, int offset) {
        Criminal entity = new Criminal( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // handleNumber
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // number
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // type
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // area
            cursor.getInt(offset + 5), // isLife
            cursor.getInt(offset + 6), // isCrime
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // startTime
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // endTime
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // place
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // solveTime
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // inquestPlace
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // inquestReason
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // caseProcess
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // protectorName
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // protectorComany
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // protectMeasures
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // protectTime
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // spotCondition
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // weatherCondition
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // lightCondition
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // spotConduct
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // inquesterName
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // spotPeople
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // spotLeft
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // inquestCondition
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // victimName
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // lossGoods
            cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28), // recordTime
            cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29), // injury
            cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30) // witness
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Criminal entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setHandleNumber(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setNumber(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setType(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setArea(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setIsLife(cursor.getInt(offset + 5));
        entity.setIsCrime(cursor.getInt(offset + 6));
        entity.setStartTime(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setEndTime(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setPlace(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setSolveTime(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setInquestPlace(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setInquestReason(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setCaseProcess(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setProtectorName(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setProtectorComany(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setProtectMeasures(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setProtectTime(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setSpotCondition(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setWeatherCondition(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setLightCondition(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setSpotConduct(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setInquesterName(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setSpotPeople(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setSpotLeft(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setInquestCondition(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setVictimName(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setLossGoods(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setRecordTime(cursor.isNull(offset + 28) ? null : cursor.getString(offset + 28));
        entity.setInjury(cursor.isNull(offset + 29) ? null : cursor.getString(offset + 29));
        entity.setWitness(cursor.isNull(offset + 30) ? null : cursor.getString(offset + 30));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Criminal entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Criminal entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Criminal entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
