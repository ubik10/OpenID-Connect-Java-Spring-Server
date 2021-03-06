/*******************************************************************************
 * Copyright 2012 The MITRE Corporation
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.mitre.jwt.signer;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum to translate between the JWS defined algorithm names and the JSE algorithm names
 * 
 * @author jricher
 *
 */
public enum JwsAlgorithm {
	
	// PLAINTEXT
	NONE("plaintext", "none"),

	// HMAC
	HS256("HMACSHA256", "HS256"), 
	HS384("HMACSHA384", "HS384"), 
	HS512("HMACSHA512", "HS512"),
	// RSA
	RS256("SHA256withRSA", "RS256"), 
	RS384("SHA384withRSA", "RS384"), 
	RS512("SHA512withRSA", "RS512");
	

	private static final Map<String, JwsAlgorithm> jwaLookup = new HashMap<String, JwsAlgorithm>();
	private static final Map<String, JwsAlgorithm> jceLookup = new HashMap<String, JwsAlgorithm>();
	static {
		for (JwsAlgorithm alg : JwsAlgorithm.values()) {
	        jwaLookup.put(alg.getJwaName(), alg);
	        jceLookup.put(alg.getStandardName(), alg);
        }
	}
	
	/**
	 * Returns the Algorithm for the JWS-registered name
	 * 
	 * @param name
	 * @return
	 */
	public static JwsAlgorithm getByJwaName(String name) {
		return jwaLookup.get(name);
	}
	
	public static JwsAlgorithm getByStandardName(String name) {
		return jceLookup.get(name);
	}

	private final String standardName;
	private final String jwaName;
	
	/**
	 * Constructor of JwsAlgorithm
	 * 
	 * @param standardName
	 */
	JwsAlgorithm(String standardName, String jwaName) {
		this.standardName = standardName;
		this.jwaName = jwaName;
	}

	/**
	 * Return the Java standard JwsAlgorithm name
	 * 
	 * @return
	 */
	public String getStandardName() {
		return standardName;
	}

	/**
	 * Return the JWA Standard name
	 * @return
	 */
	public String getJwaName() {
		return jwaName;
	}

}
