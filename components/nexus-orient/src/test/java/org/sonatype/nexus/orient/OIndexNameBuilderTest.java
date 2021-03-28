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
package org.sonatype.nexus.orient;

import org.sonatype.goodies.testsupport.TestSupport;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OIndexNameBuilderTest
    extends TestSupport
{
  OIndexNameBuilder underTest = new OIndexNameBuilder();

  @Test
  public void testBuild() {
    String name = underTest.
        type("myclass").
        property("property1").
        property("property2").
        build();

    assertThat(name, is("myclass_property1_property2_idx"));
  }

  @Test(expected = IllegalStateException.class)
  public void testBuildWithoutProperties() {
    String name = underTest.
        type("myclass").
        build();
  }

  @Test
  public void testBuildCaseInsensitive() {
    String name = underTest.
        type("myclass").
        property("property1").
        property("property2").
        caseInsensitive().
        build();

    assertThat(name, is("myclass_property1_property2_ci_idx"));
  }
}
