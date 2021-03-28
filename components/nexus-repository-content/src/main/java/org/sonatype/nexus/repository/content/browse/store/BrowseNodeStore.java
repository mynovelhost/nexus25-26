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
package org.sonatype.nexus.repository.content.browse.store;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;

import org.sonatype.nexus.datastore.api.DataSessionSupplier;
import org.sonatype.nexus.repository.browse.node.BrowseNode;
import org.sonatype.nexus.repository.content.store.ContentStoreSupport;
import org.sonatype.nexus.transaction.Transactional;

import com.google.inject.assistedinject.Assisted;

/**
 * Browse node store.
 *
 * @since 3.26
 */
@Named
public class BrowseNodeStore<T extends BrowseNodeDAO>
    extends ContentStoreSupport<T>
{
  @Inject
  public BrowseNodeStore(final DataSessionSupplier sessionSupplier,
                         @Assisted final String contentStoreName,
                         @Assisted final Class<T> daoClass)
  {
    super(sessionSupplier, contentStoreName, daoClass);
  }

  /**
   * Retrieves the browse nodes directly under the given hierarchical display path.
   *
   * @param repositoryId the repository containing the browse nodes
   * @param displayPath the hierarchical path leading up to the browse nodes
   * @param limit when positive limits the number of browse nodes returned
   * @param filter optional filter to apply to the browse nodes
   * @param filterParams parameter map for the optional filter
   * @return browse nodes found directly under the display path
   */
  @Transactional
  public List<BrowseNode> getByDisplayPath(
      final int repositoryId,
      final List<String> displayPath,
      final int limit,
      @Nullable final String filter,
      @Nullable final Map<String, Object> filterParams)
  {
    return dao().getByDisplayPath(repositoryId, displayPath, limit, filter, filterParams);
  }

  /**
   * Does a browse node already exist for this component?
   */
  @Transactional
  public boolean hasComponentNode(final int componentId) {
    return dao().hasComponentNode(componentId);
  }

  /**
   * Does a browse node already exist for this asset?
   */
  @Transactional
  public boolean hasAssetNode(final int assetId) {
    return dao().hasAssetNode(assetId);
  }

  /**
   * Merges the given browse node with the tree of nodes in the content data store.
   *
   * @param browseNode the node to merge
   */
  @Transactional
  public void mergeBrowseNode(final BrowseNodeData browseNode) {
    dao().mergeBrowseNode(browseNode);
  }

  /**
   * Deletes all browse nodes in the given repository from the content data store.
   *
   * @param repositoryId the repository containing the browse nodes
   * @return {@code true} if any browse nodes were deleted
   */
  @Transactional
  public boolean deleteBrowseNodes(final int repositoryId) {
    log.debug("Deleting all browse nodes in repository {}", repositoryId);
    boolean deleted = false;
    while (dao().deleteBrowseNodes(repositoryId, deleteBatchSize())) {
      commitChangesSoFar();
      deleted = true;
    }
    log.debug("Deleted all browse nodes in repository {}", repositoryId);
    return deleted;
  }
}
