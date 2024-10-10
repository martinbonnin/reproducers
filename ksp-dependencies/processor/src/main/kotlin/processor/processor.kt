package processor

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated


class MyProcessorProvider: SymbolProcessorProvider {
  override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
    return MyProcessor(environment.logger)
  }
}

class MyProcessor(val logger: KSPLogger) : SymbolProcessor {
  @OptIn(KspExperimental::class)
  override fun process(resolver: Resolver): List<KSAnnotated> {
    val symbols = resolver.getDeclarationsFromPackage("lib", )

    logger.warn("Symbols:")
    symbols.forEach {
      logger.warn("-: $it")
    }

    return emptyList()
  }
}