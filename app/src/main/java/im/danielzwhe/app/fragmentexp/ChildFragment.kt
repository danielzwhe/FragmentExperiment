package im.danielzwhe.app.fragmentexp

class ChildFragment(private val transactionHistory: String) :
    BaseFragment(R.layout.fragment_generic_layout) {

    override fun title(): String? = null

    override fun transactionHistory(): String = transactionHistory

    override fun fragmentContainerId(): Int = R.id.fragmentContainerView
}