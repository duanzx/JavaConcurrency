package datastruct.bintree.java;

/**
 * 定义二叉树的结构
 */
public interface BinNode {


    //while 一直向上更新节点高度
    public void updateAbove();

    public void insertAsRc();

    public void insertAsLc();

    //先序遍历，按照某种次序访问树中各个节点，每个节点被访问恰好一次

}
