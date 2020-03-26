import kotlin.contracts.contract

fun main() {
  val viewModel = createViewModel()
  assertNotNull(viewModel)
  assertIs<ViewModel.Content>(viewModel)
  assertTitleEquals(viewModel.title, "Title")
}

fun assertNotNull(instance: Any?) {
  contract {
    returns() implies (instance != null)
  }
  if (instance == null) throw AssertionError()
}

inline fun <reified T> assertIs(instance: Any) {
  contract {
    returns() implies (instance is T)
  }
  if (instance !is T) throw AssertionError()
}

fun assertTitleEquals(title: String?, expected: String?) {
  if (title != expected) throw AssertionError()
}

fun createViewModel(): ViewModel? = ViewModel.Content(title = "Title", text = "Text")

sealed class ViewModel {
  data class Content(
    val title: String?,
    val text: String?
  ) : ViewModel()

  object Loading : ViewModel()
}
