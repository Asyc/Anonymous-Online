package me.asycc.anonymousonline.common.utils;

/**
 *
 * A class to store information about the version of something.
 *
 * @author Asyc
 * @since 3/4/2019
 */
public class Version {


    /**
     * The major version, minor version, and patch number.
     */
    private final int major, minor, patch;

    /**
     *
     * @param major The major version.
     * @param minor The minor version.
     * @param patch The patch number.
     */
    public Version(final int major, final int minor, final int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    /**
     * @return Returns the major version.
     */
    public int getMajor() {
        return major;
    }

    /**
     * @return Returns the minor version.
     */
    public int getMinor() {
        return minor;
    }

    /**
     * @return Returns the patch number.
     */
    public int getPatch() {
        return patch;
    }

    /**
     * @return Puts the major version, minor version, and patch number in a formatted string like
     * major.minor.patch
     */
    @Override
    public String toString() {
        return major + "." + minor + "." + patch;
    }

    /**
     * @param obj The object to compare this object with.
     * @return Returns whether the two objects are the same version or not.
     */
    @Override
    public boolean equals(Object obj) {
        return this.toString().contentEquals(obj.toString());
    }
}
