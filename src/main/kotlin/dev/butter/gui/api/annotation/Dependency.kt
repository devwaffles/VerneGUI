package dev.butter.gui.api.annotation

/**
 * Marks a field as a dependency for a GUI.
 * At the moment, this is used for lightweight
 * dependency injection, with no specific way
 * to inject collections. In the future, this
 * will be supported by utilizing Guice.
 *
 * For static GUIs, this annotation ensures that
 * fields marked with this annotation, which are
 * not player-specific or singleton, are
 * injected using the DependencyInit function
 * that was registered. This occurs before the
 * createContents method is called.
 *
 * For dynamic GUIs, this annotation ensures that
 * fields marked with this annotation, which are
 * player-specific or singleton, are injected using
 * the PlayerDependencyInit function.
 *
 * @see dev.butter.gui.api.base.BaseGUI
 * @see dev.butter.gui.api.base.BaseGUI.createContents
 * @see dev.butter.gui.api.VerneGUI.registerDependency
 * @see dev.butter.gui.api.VerneGUI.registerPlayerDependency
 * @see dev.butter.gui.api.VerneGUI.registerSingleton
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class Dependency