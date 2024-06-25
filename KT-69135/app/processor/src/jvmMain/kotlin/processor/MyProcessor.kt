package processor

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated


class MySymbolProcessor(private val logger: KSPLogger) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val symbols = resolver.getSymbolsWithAnnotation("annotations.MyAnnotation").toList()

        check(symbols.isNotEmpty()) {
            "No symbol with MyAnnotation found"
        }

        logger.warn("found ${symbols.size} symbols")
        return emptyList()
    }
}

class MyProcessorProvider: SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return MySymbolProcessor(environment.logger)
    }
}
