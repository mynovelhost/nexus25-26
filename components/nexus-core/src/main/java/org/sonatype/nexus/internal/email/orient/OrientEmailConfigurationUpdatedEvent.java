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
package org.sonatype.nexus.internal.email.orient;

import org.sonatype.nexus.common.entity.EntityMetadata;
import org.sonatype.nexus.common.entity.EntityUpdatedEvent;
import org.sonatype.nexus.email.EmailConfiguration;
import org.sonatype.nexus.internal.email.EmailConfigurationEvent;

/**
 * Emitted when an {@link EmailConfiguration} entity has been updated.
 *
 * @since 3.2
 */
public class OrientEmailConfigurationUpdatedEvent
    extends EntityUpdatedEvent
    implements EmailConfigurationEvent
{
  public OrientEmailConfigurationUpdatedEvent(final EntityMetadata metadata) {
    super(metadata);
  }

  @Override
  public EmailConfiguration getEmailConfiguration() {
    return getEntity();
  }
}
