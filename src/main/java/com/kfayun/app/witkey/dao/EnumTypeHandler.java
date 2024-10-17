/**
 * 云联创威客系统
 * 
 * Copyright 2015 云联创科技
 */
package com.kfayun.app.witkey.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.kfayun.app.witkey.BaseEnum;

/**
 * 枚举类型处理
 * 实现BaseEnum枚举类别与int类型的自动互转換。
 * 
 * @author billy (billy_zh@126.com)
 */
public class EnumTypeHandler extends BaseTypeHandler<BaseEnum> {
	
	private Class<BaseEnum> type; 
    
    public EnumTypeHandler(Class<BaseEnum> type){
        this.type = type;
    }
    
	/**
	 * BaseEnum枚举转int
	 */
	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, BaseEnum parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setInt(i, parameter.getConstant());
	}

	/**
	 * int转BaseEnum枚举
	 */
	@Override
	public BaseEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
		int constant = rs.getInt(columnName);
		return rs.wasNull() ? null : getEnum(constant);
	}

	/**
	 * int转BaseEnum枚举
	 */
	@Override
	public BaseEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		int constant = rs.getInt(columnIndex);
		return rs.wasNull() ? null : getEnum(constant);
	}

	/**
	 * int转BaseEnum枚举
	 */
	@Override
	public BaseEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		int constant = cs.getInt(columnIndex);
		return cs.wasNull() ? null : getEnum(constant);
	}

	private BaseEnum getEnum(int constant) {
		BaseEnum[] enums = type.getEnumConstants();
        for (BaseEnum em : enums) {
            if(em.getConstant() == constant) {
                return em;
            }
        }
        return null;
	}

}
