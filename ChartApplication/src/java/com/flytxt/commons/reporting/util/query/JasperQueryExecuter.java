/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * @(#) $Id:
 * Copyright Flytxt Technologies Pvt Limited. All Rights Reserved.
 *
 * This Software is the proprietary information of Flytxt technologies Pvt Limited.
 * Use is subject to License terms.
 *
 */
package com.flytxt.commons.reporting.util.query;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRQuery;
import net.sf.jasperreports.engine.JRQueryChunk;
import net.sf.jasperreports.engine.design.JRDesignParameter;




/**
 *
 * @author Merrill George (merrill.george@gmail.com)
 */
public class JasperQueryExecuter {
    /**
	 *
	 */
	private JRQuery query = null;
	private Map<String,JRDesignParameter> parametersMap = new HashMap();
	private Map<String,Object> parameterValues = null;
	private String queryString = "";
	private List parameterNames = new ArrayList();


	/**
	 *
	 */
	protected JasperQueryExecuter(JRQuery query, Map<String,JRDesignParameter> parameters, Map<String,Object> values)
	{
		this.query = query;
		this.parametersMap = parameters;
		this.parameterValues = values;

		this.parseQuery();
	}


	/**
	 *
	 */
	public static PreparedStatement getStatement(
		JRQuery query,
		Map<String,JRDesignParameter> parameters,
		Map<String,Object> values,
		Connection conn
		) throws JRException
	{
		PreparedStatement pstmt = null;

		if (conn != null)
		{
			JasperQueryExecuter queryExecuter =
				new JasperQueryExecuter(
					query,
					parameters,
					values
					);

			pstmt = queryExecuter.getStatement(conn);
		}

		return pstmt;
	}


	/**
	 *
	 */
	private void parseQuery()
	{
		queryString = "";
		parameterNames = new ArrayList();

		if (query != null)
		{
			JRQueryChunk[] chunks = query.getChunks();
			if (chunks != null && chunks.length > 0)
			{
				StringBuffer sbuffer = new StringBuffer();
				JRQueryChunk chunk = null;
				for(int i = 0; i < chunks.length; i++)
				{
					chunk = chunks[i];
					switch (chunk.getType())
					{
						case JRQueryChunk.TYPE_PARAMETER_CLAUSE :
						{
							String parameterName = chunk.getText();
							Object parameterValue = parameterValues.get(parameterName);
							sbuffer.append(String.valueOf(parameterValue));
							//parameterNames.add(parameterName);
							break;
						}
						case JRQueryChunk.TYPE_PARAMETER :
						{
							Object parameterValue = parameterValues.get(chunk.getText());
                                                        
                                                        if(parameterValue!=null && Collection.class.isAssignableFrom(parameterValue.getClass()) ){

                                                           
                                                            prepareArrayVals(chunk.getText(),sbuffer,(Collection)parameterValue);
                                                           

                                                        }else{
                                                            sbuffer.append("?");
                                                            parameterNames.add(chunk.getText());
                                                        }
							break;
						}
						case JRQueryChunk.TYPE_TEXT :
						default :
						{
							sbuffer.append(chunk.getText());
							break;
						}
					}
				}

				queryString = sbuffer.toString();
			}
		}
	}


	/**
	 *
	 */
	private PreparedStatement getStatement(Connection conn) throws JRException
	{
		PreparedStatement pstmt = null;

		if (queryString != null && queryString.trim().length() > 0)
		{
			try
			{
				pstmt = conn.prepareStatement(queryString);

				if (parameterNames != null && parameterNames.size() > 0)
				{
					JRParameter parameter = null;
					String parameterName = null;
					Class clazz = null;
					Object parameterValue = null;
					for(int i = 0; i < parameterNames.size(); i++)
					{
						parameterName = (String)parameterNames.get(i);
						parameter = (JRParameter)parametersMap.get(parameterName);
						clazz = parameter.getValueClass();
						//FIXMEparameterValue = jrParameter.getValue();
						parameterValue = parameterValues.get(parameterName);

						if ( clazz.equals(java.lang.Object.class) )
						{
							if (parameterValue == null)
							{
								pstmt.setNull(i + 1, Types.JAVA_OBJECT);
							}
							else
							{
								pstmt.setObject(i + 1, parameterValue);
							}
						}
						else if ( clazz.equals(java.lang.Boolean.class) )
						{
							if (parameterValue == null)
							{
								pstmt.setNull(i + 1, Types.BIT);
							}
							else
							{
								pstmt.setBoolean(i + 1, ((Boolean)parameterValue).booleanValue());
							}
						}
						else if ( clazz.equals(java.lang.Byte.class) )
						{
							if (parameterValue == null)
							{
								pstmt.setNull(i + 1, Types.TINYINT);
							}
							else
							{
								pstmt.setByte(i + 1, ((Byte)parameterValue).byteValue());
							}
						}
						else if ( clazz.equals(java.lang.Double.class) )
						{
							if (parameterValue == null)
							{
								pstmt.setNull(i + 1, Types.DOUBLE);
							}
							else
							{
								pstmt.setDouble(i + 1, ((Double)parameterValue).doubleValue());
							}
						}
						else if ( clazz.equals(java.lang.Float.class) )
						{
							if (parameterValue == null)
							{
								pstmt.setNull(i + 1, Types.FLOAT);
							}
							else
							{
								pstmt.setFloat(i + 1, ((Float)parameterValue).floatValue());
							}
						}
						else if ( clazz.equals(java.lang.Integer.class) )
						{
							if (parameterValue == null)
							{
								pstmt.setNull(i + 1, Types.INTEGER);
							}
							else
							{
								pstmt.setInt(i + 1, ((Integer)parameterValue).intValue());
							}
						}
						else if ( clazz.equals(java.lang.Long.class) )
						{
							if (parameterValue == null)
							{
								pstmt.setNull(i + 1, Types.BIGINT);
							}
							else
							{
								pstmt.setLong(i + 1, ((Long)parameterValue).longValue());
							}
						}
						else if ( clazz.equals(java.lang.Short.class) )
						{
							if (parameterValue == null)
							{
								pstmt.setNull(i + 1, Types.SMALLINT);
							}
							else
							{
								pstmt.setShort(i + 1, ((Short)parameterValue).shortValue());
							}
						}
						else if ( clazz.equals(java.math.BigDecimal.class) )
						{
							if (parameterValue == null)
							{
								pstmt.setNull(i + 1, Types.DECIMAL);
							}
							else
							{
								pstmt.setBigDecimal(i + 1, (BigDecimal)parameterValue);
							}
						}
						else if ( clazz.equals(java.lang.String.class) )
						{
							if (parameterValue == null)
							{
								pstmt.setNull(i + 1, Types.VARCHAR);
							}
							else
							{
								pstmt.setString(i + 1, parameterValue.toString());
							}
						}
						else if ( clazz.equals(java.util.Date.class) )
						{
							if (parameterValue == null)
							{
								pstmt.setNull(i + 1, Types.DATE);
							}
							else
							{
								pstmt.setDate( i + 1, new java.sql.Date( ((java.util.Date)parameterValue).getTime() ) );
							}
						}
						else if ( clazz.equals(java.sql.Timestamp.class) )
						{
							if (parameterValue == null)
							{
								pstmt.setNull(i + 1, Types.TIMESTAMP);
							}
							else
							{
								pstmt.setTimestamp( i + 1, (java.sql.Timestamp)parameterValue );
							}
						}
						else if ( clazz.equals(java.sql.Time.class) )
						{
							if (parameterValue == null)
							{
								pstmt.setNull(i + 1, Types.TIME);
							}
							else
							{
								pstmt.setTime( i + 1, (java.sql.Time)parameterValue );
							}
						}
						else
						{
							throw new JRException("Parameter type not supported in query : " + parameterName + " class " + clazz.getName());
						}
					}
				}
			}
			catch (SQLException e)
			{
				throw new JRException("Error preparing statement for executing the report query : " + "\n\n" + queryString + "\n\n", e);
			}
		}

		return pstmt;
	}

  
   

    private void prepareArrayVals(String paramName,StringBuffer sbuffer, Collection collection) {
       if(collection.isEmpty()){
            sbuffer.append("(?)");
            parameterValues.put(paramName,null);
            parameterNames.add(paramName);
            parametersMap.remove(paramName);
            JRDesignParameter jrParameter = new JRDesignParameter();
            jrParameter.setName(paramName);
            jrParameter.setValueClass(String.class);
            parametersMap.put(paramName,jrParameter);
       }else{
           int i=0;
           sbuffer.append("(");
           parameterValues.remove(paramName);
           parametersMap.remove(paramName);
           Map<String,Object> newParamValues = new HashMap<String, Object>();
           for(Object obj : collection){
               i++;
               String newParamName = paramName+"_"+i;
                parameterNames.add(newParamName);
               sbuffer.append("?");
               if(obj.getClass().equals(Integer.class)){
                    newParamValues.put(newParamName,(Integer)obj);
               }

               if(i < collection.size()){
                    sbuffer.append(",");
               }
           }
           parametersMap.putAll(buildJRDesignParameters(newParamValues));
           parameterValues.putAll(newParamValues);
           sbuffer.append(")");
       }
    }


     public static  Map<String,JRDesignParameter> buildJRDesignParameters(Map<String,Object> parameters)
	{
		// convert parameters to JRDesignParameters so they can be parsed
		HashMap<String,JRDesignParameter> jrParameters = new HashMap<String,JRDesignParameter>();

		Iterator<String> iterator = parameters.keySet().iterator();
		while (iterator.hasNext())
		{
			String key = iterator.next();
			Object value = parameters.get(key);

			if (value != null)
			{
				JRDesignParameter jrParameter = new JRDesignParameter();
				jrParameter.setName(key);
				jrParameter.setValueClass(value.getClass());

				jrParameters.put(jrParameter.getName(), jrParameter);
			}
		}

		return jrParameters;
	}
}
