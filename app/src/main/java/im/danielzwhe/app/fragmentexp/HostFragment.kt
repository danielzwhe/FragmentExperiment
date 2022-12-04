package im.danielzwhe.app.fragmentexp

class HostFragment(private val transactionHistory: String) :
    BaseFragment(R.layout.fragment_generic_layout) {

    override fun title(): String {
        return "Host Fragment"
    }

    override fun transactionHistory(): String = transactionHistory

    override fun fragmentContainerId(): Int = R.id.fragmentContainerView
}