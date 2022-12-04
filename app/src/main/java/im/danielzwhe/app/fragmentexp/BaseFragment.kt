package im.danielzwhe.app.fragmentexp

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton
import com.google.android.material.switchmaterial.SwitchMaterial

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    private lateinit var backgroundLayout: View
    private lateinit var titleTextView: TextView
    private lateinit var transactionHistoryTextView: TextView
    private lateinit var parentFragmentManagerAddButton: MaterialButton
    private lateinit var parentFragmentManagerReplaceButton: MaterialButton
    private lateinit var childFragmentManagerAddButton: MaterialButton
    private lateinit var childFragmentManagerReplaceButton: MaterialButton
    private lateinit var addToBackStackToggle: SwitchMaterial
    private lateinit var setPrimaryNavigationFragmentToggle: SwitchMaterial

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findViews()
        initView()
    }

    private fun findViews() {
        view?.run {
            backgroundLayout = findViewById(R.id.backgroundLayout)
            titleTextView = findViewById(R.id.titleLabel)
            transactionHistoryTextView = findViewById(R.id.transactionHistory)
            parentFragmentManagerAddButton = findViewById(R.id.parentFragmentManagerAddButton)
            parentFragmentManagerReplaceButton =
                findViewById(R.id.parentFragmentManagerReplaceButton)
            childFragmentManagerAddButton = findViewById(R.id.childFragmentManagerAddButton)
            childFragmentManagerReplaceButton = findViewById(R.id.childFragmentManagerReplaceButton)
            addToBackStackToggle = findViewById(R.id.addToBackStackToggle)
            setPrimaryNavigationFragmentToggle =
                findViewById(R.id.setPrimaryNavigationFragmentToggle)
        }
    }

    private fun initView() {
        titleTextView.text = title() ?: (this.toString().substringBefore("("))
        transactionHistoryTextView.text = transactionHistory()
        backgroundLayout.background = randomColorDrawable()

        parentFragmentManagerAddButton.setOnClickListener {
            addByParentFragmentManager()
        }

        parentFragmentManagerReplaceButton.setOnClickListener {
            replaceByParentFragmentManager()
        }

        childFragmentManagerAddButton.setOnClickListener {
            addByChildFragmentManager()
        }

        childFragmentManagerReplaceButton.setOnClickListener {
            replaceByChildFragmentManager()
        }
    }

    protected abstract fun title(): String?

    protected abstract fun transactionHistory(): String

    protected abstract fun fragmentContainerId(): Int

    private fun randomColorDrawable(): ColorDrawable = Utils.randomColor()

    private fun isAddToBackStack(): Boolean {
        return addToBackStackToggle.isChecked
    }

    private fun isSetPrimaryNavigationFragment(): Boolean {
        return setPrimaryNavigationFragmentToggle.isChecked
    }

    protected fun addByParentFragmentManager() {
        val history =
            StringBuilder(transactionHistory())
                .append("add via pfm, backStack: ${isAddToBackStack()}, setPrimary:${isSetPrimaryNavigationFragment()}")
                .append("\n")
                .toString()
        val fm = ChildFragment(history)
        val tag = fm::class.java.canonicalName
        parentFragmentManager.beginTransaction().apply {
            add(fragmentContainerId(), fm, tag)
            if (isAddToBackStack()) {
                addToBackStack(tag)
            }
            if (isSetPrimaryNavigationFragment()) {
                setPrimaryNavigationFragment(fm)
            }
            commitAllowingStateLoss()
        }
    }

    protected fun replaceByParentFragmentManager() {
        val history =
            StringBuilder(transactionHistory())
                .append("replace via pfm, backStack: ${isAddToBackStack()}, setPrimary:${isSetPrimaryNavigationFragment()}")
                .append("\n")
                .toString()
        val fm = ChildFragment(history)
        val tag = fm::class.java.canonicalName
        parentFragmentManager.beginTransaction().apply {
            replace(fragmentContainerId(), fm, tag)
            if (isAddToBackStack()) {
                addToBackStack(tag)
            }
            if (isSetPrimaryNavigationFragment()) {
                setPrimaryNavigationFragment(fm)
            }
            commitAllowingStateLoss()
        }
    }

    protected fun addByChildFragmentManager() {
        val history =
            StringBuilder(transactionHistory())
                .append("add via cfm, backStack: ${isAddToBackStack()}, setPrimary:${isSetPrimaryNavigationFragment()}")
                .append("\n")
                .toString()
        val fm = ChildFragment(history)
        val tag = fm::class.java.canonicalName
        childFragmentManager.beginTransaction().apply {
            add(fragmentContainerId(), fm, tag)
            if (isAddToBackStack()) {
                addToBackStack(tag)
            }
            if (isSetPrimaryNavigationFragment()) {
                setPrimaryNavigationFragment(fm)
            }
            commitAllowingStateLoss()
        }
    }

    protected fun replaceByChildFragmentManager() {
        val history =
            StringBuilder(transactionHistory())
                .append("replace via cfm, backStack: ${isAddToBackStack()}, setPrimary:${isSetPrimaryNavigationFragment()}")
                .append("\n")
                .toString()
        val fm = ChildFragment(history)
        val tag = fm::class.java.canonicalName
        childFragmentManager.beginTransaction().apply {
            replace(fragmentContainerId(), fm, tag)
            if (isAddToBackStack()) {
                addToBackStack(tag)
            }
            if (isSetPrimaryNavigationFragment()) {
                setPrimaryNavigationFragment(fm)
            }
            commitAllowingStateLoss()
        }
    }
}