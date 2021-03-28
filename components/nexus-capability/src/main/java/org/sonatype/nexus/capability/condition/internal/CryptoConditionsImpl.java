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
package org.sonatype.nexus.capability.condition.internal;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.capability.Condition;
import org.sonatype.nexus.capability.condition.CryptoConditions;
import org.sonatype.nexus.common.event.EventManager;
import org.sonatype.nexus.crypto.CryptoHelper;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Default implementation of {@link CryptoConditions}.
 *
 * @since 2.7
 */
@Named
@Singleton
public class CryptoConditionsImpl
    implements CryptoConditions
{
  private final EventManager eventManager;

  private final CryptoHelper crypto;

  @Inject
  public CryptoConditionsImpl(final EventManager eventManager,
                          final CryptoHelper crypto)
  {
    this.eventManager = checkNotNull(eventManager);
    this.crypto = checkNotNull(crypto);
  }

  @Override
  public Condition requireCipher(final String algorithm) {
    return new CipherRequiredCondition(eventManager, crypto, algorithm);
  }

  @Override
  public Condition highStrengthCipherKey(final String algorithm) {
    return new CipherKeyHighStrengthCondition(eventManager, crypto, algorithm);
  }

  @Override
  public Condition unlimitedStrengthCipherKey(final String algorithm) {
    return new CipherKeyUnlimitedStrengthCondition(eventManager, crypto, algorithm);
  }
}
