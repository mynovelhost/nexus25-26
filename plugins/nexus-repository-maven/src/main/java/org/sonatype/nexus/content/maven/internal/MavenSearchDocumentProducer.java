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
package org.sonatype.nexus.content.maven.internal;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.sonatype.nexus.repository.content.fluent.FluentComponent;
import org.sonatype.nexus.repository.content.search.DefaultSearchDocumentProducer;
import org.sonatype.nexus.repository.content.search.SearchDocumentExtension;
import org.sonatype.nexus.repository.maven.internal.Maven2Format;

import static org.sonatype.nexus.repository.maven.internal.Attributes.P_BASE_VERSION;
import static org.sonatype.nexus.repository.maven.internal.Constants.SNAPSHOT_VERSION_SUFFIX;

/**
 * Maven implementation of {@link DefaultSearchDocumentProducer}
 *
 * @since 3.26
 */
@Singleton
@Named(Maven2Format.NAME)
public class MavenSearchDocumentProducer
    extends DefaultSearchDocumentProducer
{
  @Inject
  public MavenSearchDocumentProducer(final Set<SearchDocumentExtension> documentExtensions) {
    super(documentExtensions);
  }

  @Override
  protected boolean isPrerelease(final FluentComponent component) {
    String baseVersion = (String) component.attributes().child(Maven2Format.NAME).get(P_BASE_VERSION);
    if (baseVersion == null) {
      return false;
    }
    return baseVersion.endsWith(SNAPSHOT_VERSION_SUFFIX);
  }
}
