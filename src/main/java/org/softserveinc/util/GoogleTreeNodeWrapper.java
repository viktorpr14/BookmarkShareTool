package org.softserveinc.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by okush on 2/3/15.
 */
public class GoogleTreeNodeWrapper {
    private GoogleRoots roots= new GoogleRoots();
    private int version=1;

    public GoogleRoots getRoots() {
        return roots;
    }

    public void setRoots(GoogleRoots roots) {
        this.roots = roots;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public  class GoogleRoots{
        private GoogleTreeNode bookmark_bar = new GoogleTreeNode();
        private GoogleTreeNode other = new GoogleTreeNode();
        private GoogleTreeNode synced = new GoogleTreeNode();

        public GoogleRoots() {
        }

        public GoogleTreeNode getBookmark_bar() {
            return bookmark_bar;
        }

        public void setBookmark_bar(GoogleTreeNode bookmark_bar) {
            this.bookmark_bar = bookmark_bar;
        }

        public GoogleTreeNode getOther() {
            return other;
        }

        public void setOther(GoogleTreeNode other) {
            this.other = other;
        }

        public GoogleTreeNode getSynced() {
            return synced;
        }

        public void setSynced(GoogleTreeNode synced) {
            this.synced = synced;
        }
    }
}
