package com.taotao.cloud.workflow.api.database.enums.datatype.viewshow;


import com.taotao.cloud.workflow.api.database.enums.datatype.DtMySQL;

public enum DataTypeEnum {

	/**
	 * 如{主类型},{次类型}:({默认字符长度},{限制长度}(*:不允许设置))
	 */

	/**
	 * 字符
	 */
	VARCHAR(
		ViewDataTypeConst.VARCHAR,
		DtMySQL.VARCHAR,
		DtOracle.VARCHAR2,
		DtSQLServer.VARCHAR,
		DtDM.VARCHAR,
		DtKingbaseES.VARCHAR,
		DtPostgreSQL.VARCHAR
	),
	/**
	 * 日期时间 日期统一不指定长度
	 */
	DATE_TIME(
		ViewDataTypeConst.DATE_TIME,
		DtMySQL.DATA_TIME,
		DtOracle.DATA,
		DtSQLServer.DATA_TIME,
		DtDM.DATA_TIME,
		DtKingbaseES.TIME_STAMP,
		DtPostgreSQL.TIME_STAMP
	),
	/**
	 * 浮点
	 */
	DECIMAL(
		ViewDataTypeConst.DECIMAL,
		DtMySQL.DECIMAL,
		DtOracle.NUMBER_DECIMAL,
		DtSQLServer.DECIMAL,
		DtDM.DECIMAL,
		DtKingbaseES.NUMERIC,
		DtPostgreSQL.NUMERIC
	),
	/**
	 * 文本
	 */
	TEXT(
		ViewDataTypeConst.TEXT,
		DtMySQL.TEXT,
		DtOracle.CLOB,
		DtSQLServer.TEXT,
		DtDM.TEXT,
		DtKingbaseES.TEXT,
		DtPostgreSQL.TEXT
	),
	/**
	 * 整型 SqlServer、PostGre:int不能指定长度
	 */
	INT(
		ViewDataTypeConst.INT,
		DtMySQL.INT,
		DtOracle.NUMBER_INT,
		DtSQLServer.INT,
		DtDM.INT,
		DtKingbaseES.INTEGER,
		DtPostgreSQL.INT4
	),
	/**
	 * 长整型
	 */
	BIGINT(
		ViewDataTypeConst.BIGINT,
		DtMySQL.BIGINT,
		DtOracle.NUMBER_BIG,
		DtSQLServer.BIGINT,
		DtDM.BIGINT,
		DtKingbaseES.BIGINT,
		DtPostgreSQL.INT8
	),
	/**
	 * oracle数字类型
	 */
	ORACLE_NUMBER(
		ViewDataTypeConst.ORACLE_NUMBER,
		null,
		DtOracle.NUMBER_VIEW,
		null,
		null,
		null,
		null
	);

	private final String viewFieldType;

	private final DtMySQL dtMySQL;

	private final DtOracle dtOracle;

	private final DtSQLServer dtSQLServer;

	private final DtDM dtDM;

	private final DtKingbaseES dtKingbaseES;

	private final DtPostgreSQL dtPostgreSQL;

	DataTypeEnum(String viewFieldType, DtMySQL dtMySQL, DtOracle dtOracle,
		DtSQLServer dtSQLServer, DtDM dtDM, DtKingbaseES dtKingbaseES,
		DtPostgreSQL dtPostgreSQL) {
		this.viewFieldType = viewFieldType;
		this.dtMySQL = dtMySQL;
		this.dtOracle = dtOracle;
		this.dtSQLServer = dtSQLServer;
		this.dtDM = dtDM;
		this.dtKingbaseES = dtKingbaseES;
		this.dtPostgreSQL = dtPostgreSQL;
	}

	/**
	 * 获取数据库类型 通过view字段类型
	 *
	 * @param viewFieldType view字段类型
	 * @param db            数据基类
	 * @return view类型对象的数据类型
	 */
	public static DataTypeModel getDataTypeModel(String viewFieldType, DbBase db)
		throws DataException {
		if (StringUtil.isNotNull(viewFieldType)) {
			for (DataTypeEnum value : DataTypeEnum.values()) {
				if (value.getViewFieldType().equalsIgnoreCase(viewFieldType)) {
					return db.getDataTypeModel(value);
				}
			}
		}
		return null;
	}

	public String getViewFieldType() {
		return viewFieldType;
	}

	/*============== 以下是6个反射方法,在DbBase里的getDataTypeModel方法使用，不要删除 ==============*/

	public DtInterface getDtDM() {
		return dtDM;
	}

	public DtInterface getDtMySQL() {
		return dtMySQL;
	}

	public DtInterface getDtOracle() {
		return dtOracle;
	}

	public DtInterface getDtKingbaseES() {
		return dtKingbaseES;
	}

	public DtInterface getDtSQLServer() {
		return dtSQLServer;
	}

	public DtInterface getDtPostgreSQL() {
		return dtPostgreSQL;
	}

	/*============================*/

}