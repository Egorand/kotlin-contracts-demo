Links:

- KEEP-139 (design proposal): https://github.com/Kotlin/KEEP/blob/master/proposals/kotlin-contracts.md
- KEEP-139 (Github): https://github.com/Kotlin/KEEP/issues/139
- Relevant YouTrack issues:
    - https://youtrack.jetbrains.com/issue/KT-14397
    - https://youtrack.jetbrains.com/issue/KT-7566
    - https://youtrack.jetbrains.com/issue/KT-21481
    - https://youtrack.jetbrains.com/issue/KT-8889
    - https://youtrack.jetbrains.com/issue/KT-7186
    - https://youtrack.jetbrains.com/issue/KT-6592

Current status (excerpts from KEEP):

- Syntax for declaring contracts (i.e. Contracts DSL) is unstable at the moment, and it's possible that it will be
completely changed in the future. It means that writing your own contracts isn't suited for production usage yet.
- Binary representation (in Kotlin Metadata) is stable enough and actually is a part of stdlib already. It won't be
changed without a graceful migration cycle. It means that you can depend on binary artifacts with contracts (e.g.
stdlib) with all usual compatibility guarantees.
- Semantics, as usual, may be changed only in exceptional circumstances and only after graceful migration cycle. It
means that you can rely on the analysis made with the help of contracts, as it won't get broken suddenly.

Limitations (excerpts from KEEP):

- Contracts are allowed only on functions, which are:
    - Final functions (members or top-level, not local) that override nothing (because inheritance not implemented yet).
    - Have block body (consequence of the first-statement convention).
- Compiler trusts contracts unconditionally (because verification not implemented yet); programmer is responsible for
writing correct and sound contracts.
- Only Returns(), Returns() implies condition and CallsInPlace are supported.
    - condition must be an instance-checks (is, !is) or nullability-checks (== null, != null), possibly joined with
    boolean operators (&&, ||, !).

Following functions in stdlib are annotated with contracts (excerpts from KEEP):

- run, with, apply, also, let, takeIf, takeUnless: lambda-argument is invoked in-place and exactly once
- repeat: lambda-argument is invoked in-place unknown amount of times
- assertTrue, require, check: finishes iff passed boolean argument is true
- assertFalse: finishes iff passed boolean argument is false
- assertNotNull, checkNotNull, requireNotNull: finishes iff passed argument is not null
