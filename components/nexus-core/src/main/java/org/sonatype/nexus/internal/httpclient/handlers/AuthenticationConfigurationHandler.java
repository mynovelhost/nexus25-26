/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2008-present Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.nexus.internal.httpclient.handlers;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.httpclient.config.AuthenticationConfiguration;
import org.sonatype.nexus.security.PasswordHelper;

import org.apache.ibatis.type.TypeHandler;

/**
 * MyBatis {@link TypeHandler} that maps an {@link AuthenticationConfiguration} to/from JSON.
 *
 * @since 3.21
 */
@Named
@Singleton
public class AuthenticationConfigurationHandler
    extends HttpClientConfigurationHandler<AuthenticationConfiguration>
{
  @Inject
  public AuthenticationConfigurationHandler(final PasswordHelper passwordHelper) {
    super(passwordHelper);
  }
}
