/**
 * WARNING! THIS IS A GENERATED FILE, AND WILL BE RE-GENERATED EACH TIME THE
 * AJAXBRIDGE IS RUN.
 *
 * You should keep your javascript code inside this file as light as possible, 
 * and rather keep the body of your Ajax application in separate *.js files. 
 *
 * Do make a backup of your changes, before re-generating this file (AjaxBridge 
 * will display a warning message to you).
 *
 * Please refer to the built-in documentation inside the AjaxBridge application 
 * for help on using this file.
 */
 
 
/**
 * Application "test.mxml"
 */

/**
 * The "test" javascript namespace. All the functions/variables you
 * have selected under the "test.mxml" in the tree will be
 * available as static members of this namespace object.
 */
test = {};


/**
 * Listen for the instantiation of the Flex application over the bridge
 */
FABridge.addInitializationCallback("b_test", testReady);


/**
 * Hook here all the code that must run as soon as the "test" class
 * finishes its instantiation over the bridge.
 *
 * For basic tasks, such as running a Flex method on the click of a javascript
 * button, chances are that both Ajax and Flex may well have loaded before the 
 * user actually clicks the button.
 *
 * However, using the "testReady()" is the safest way, as it will 
 * let Ajax know that involved Flex classes are available for use.
 */
function testReady() {

	// Initialize the "root" object. This represents the actual 
	// "test.mxml" flex application.
	b_test_root = FABridge["b_test"].root();
	

	// Global variables in the "test.mxml" application (converted 
	// to getters and setters)

	test.getPreloader = function () {
		return b_test_root.getPreloader();
	};


	test.getScriptTimeLimit = function () {
		return b_test_root.getScriptTimeLimit();
	};


	test.getControlBar = function () {
		return b_test_root.getControlBar();
	};


	test.getPageTitle = function () {
		return b_test_root.getPageTitle();
	};


	test.getFrameRate = function () {
		return b_test_root.getFrameRate();
	};


	test.getResetHistory = function () {
		return b_test_root.getResetHistory();
	};


	test.getUsePreloader = function () {
		return b_test_root.getUsePreloader();
	};


	test.getHistoryManagementEnabled = function () {
		return b_test_root.getHistoryManagementEnabled();
	};


	test.getScriptRecursionLimit = function () {
		return b_test_root.getScriptRecursionLimit();
	};


	test.getConstructor = function () {
		return b_test_root.getConstructor();
	};


	test.getSuper = function () {
		return b_test_root.getSuper();
	};


	test.getThis = function () {
		return b_test_root.getThis();
	};


	test.getTransitions = function () {
		return b_test_root.getTransitions();
	};


	test.getStates = function () {
		return b_test_root.getStates();
	};


	// Global functions in the "test.mxml" application

	test.getChildIndex = function(argDisplayObject) {
		return b_test_root.getChildIndex(argDisplayObject);
	};

	test.setIcon = function(argClass) {
		b_test_root.setIcon(argClass);
	};

	test.setPercentHeight = function(argNumber) {
		b_test_root.setPercentHeight(argNumber);
	};

	test.prepareToPrint = function(argIFlexDisplayObject) {
		return b_test_root.prepareToPrint(argIFlexDisplayObject);
	};

	test.setTabIndex = function(argInt) {
		b_test_root.setTabIndex(argInt);
	};

	test.setLabel = function(argString) {
		b_test_root.setLabel(argString);
	};

	test.getViewMetrics = function() {
		return b_test_root.getViewMetrics();
	};

	test.finishPrint = function(argObject, argIFlexDisplayObject) {
		b_test_root.finishPrint(argObject, argIFlexDisplayObject);
	};

	test.setToolTip = function(argString) {
		b_test_root.setToolTip(argString);
	};

	test.getUrl = function() {
		return b_test_root.getUrl();
	};

	test.getParameters = function() {
		return b_test_root.getParameters();
	};

	test.Application = function() {
		return b_test_root.Application();
	};

	test.getId = function() {
		return b_test_root.getId();
	};

	test.initialize = function() {
		b_test_root.initialize();
	};

	test.addToCreationQueue = function(argObject, argInt, argFunction, argIFlexDisplayObject) {
		b_test_root.addToCreationQueue(argObject, argInt, argFunction, argIFlexDisplayObject);
	};

	test.setPercentWidth = function(argNumber) {
		b_test_root.setPercentWidth(argNumber);
	};

	test.styleChanged = function(argString) {
		b_test_root.styleChanged(argString);
	};

	test.setViewSourceURL = function(argString) {
		b_test_root.setViewSourceURL(argString);
	};

	test.getViewSourceURL = function() {
		return b_test_root.getViewSourceURL();
	};

	test.removeEventListener = function(argString, argFunction, argBoolean) {
		b_test_root.removeEventListener(argString, argFunction, argBoolean);
	};

	test.createComponentsFromDescriptors = function(argBoolean) {
		b_test_root.createComponentsFromDescriptors(argBoolean);
	};

	test.getViewMetricsAndPadding = function() {
		return b_test_root.getViewMetricsAndPadding();
	};

	test.getMaxVerticalScrollPosition = function() {
		return b_test_root.getMaxVerticalScrollPosition();
	};

	test.getVerticalLineScrollSize = function() {
		return b_test_root.getVerticalLineScrollSize();
	};

	test.setVerticalLineScrollSize = function(argNumber) {
		b_test_root.setVerticalLineScrollSize(argNumber);
	};

	test.getIcon = function() {
		return b_test_root.getIcon();
	};

	test.setIcon = function(argClass) {
		b_test_root.setIcon(argClass);
	};

	test.regenerateStyleCache = function(argBoolean) {
		b_test_root.regenerateStyleCache(argBoolean);
	};

	test.localToContent = function(argPoint) {
		return b_test_root.localToContent(argPoint);
	};

	test.styleChanged = function(argString) {
		b_test_root.styleChanged(argString);
	};

	test.notifyStyleChangeInChildren = function(argString, argBoolean) {
		b_test_root.notifyStyleChangeInChildren(argString, argBoolean);
	};

	test.getHorizontalScrollPosition = function() {
		return b_test_root.getHorizontalScrollPosition();
	};

	test.setHorizontalScrollPosition = function(argNumber) {
		b_test_root.setHorizontalScrollPosition(argNumber);
	};

	test.getNumChildren = function() {
		return b_test_root.getNumChildren();
	};

	test.getLabel = function() {
		return b_test_root.getLabel();
	};

	test.setLabel = function(argString) {
		b_test_root.setLabel(argString);
	};

	test.setCreatingContentPane = function(argBoolean) {
		b_test_root.setCreatingContentPane(argBoolean);
	};

	test.getCreatingContentPane = function() {
		return b_test_root.getCreatingContentPane();
	};

	test.getHorizontalScrollPolicy = function() {
		return b_test_root.getHorizontalScrollPolicy();
	};

	test.setHorizontalScrollPolicy = function(argString) {
		b_test_root.setHorizontalScrollPolicy(argString);
	};

	test.contains = function(argDisplayObject) {
		return b_test_root.contains(argDisplayObject);
	};

	test.setHorizontalPageScrollSize = function(argNumber) {
		b_test_root.setHorizontalPageScrollSize(argNumber);
	};

	test.getHorizontalPageScrollSize = function() {
		return b_test_root.getHorizontalPageScrollSize();
	};

	test.globalToContent = function(argPoint) {
		return b_test_root.globalToContent(argPoint);
	};

	test.getBorderMetrics = function() {
		return b_test_root.getBorderMetrics();
	};

	test.removeChild = function(argDisplayObject) {
		return b_test_root.removeChild(argDisplayObject);
	};

	test.setAutoLayout = function(argBoolean) {
		b_test_root.setAutoLayout(argBoolean);
	};

	test.getAutoLayout = function() {
		return b_test_root.getAutoLayout();
	};

	test.addEventListener = function(argString, argFunction, argBoolean1, argInt, argBoolean2) {
		b_test_root.addEventListener(argString, argFunction, argBoolean1, argInt, argBoolean2);
	};

	test.setChildIndex = function(argDisplayObject, argInt) {
		b_test_root.setChildIndex(argDisplayObject, argInt);
	};

	test.getChildren = function() {
		return b_test_root.getChildren();
	};

	test.setDoubleClickEnabled = function(argBoolean) {
		b_test_root.setDoubleClickEnabled(argBoolean);
	};

	test.getChildByName = function(argString) {
		return b_test_root.getChildByName(argString);
	};

	test.setVerticalScrollPolicy = function(argString) {
		b_test_root.setVerticalScrollPolicy(argString);
	};

	test.getVerticalScrollPolicy = function() {
		return b_test_root.getVerticalScrollPolicy();
	};

	test.finishPrint = function(argObject, argIFlexDisplayObject) {
		b_test_root.finishPrint(argObject, argIFlexDisplayObject);
	};

	test.getVerticalScrollPosition = function() {
		return b_test_root.getVerticalScrollPosition();
	};

	test.setVerticalScrollPosition = function(argNumber) {
		b_test_root.setVerticalScrollPosition(argNumber);
	};

	test.getCreationPolicy = function() {
		return b_test_root.getCreationPolicy();
	};

	test.setCreationPolicy = function(argString) {
		b_test_root.setCreationPolicy(argString);
	};

	test.setEnabled = function(argBoolean) {
		b_test_root.setEnabled(argBoolean);
	};

	test.getContentMouseY = function() {
		return b_test_root.getContentMouseY();
	};

	test.getContentMouseX = function() {
		return b_test_root.getContentMouseX();
	};

	test.contentToLocal = function(argPoint) {
		return b_test_root.contentToLocal(argPoint);
	};

	test.validateDisplayList = function() {
		b_test_root.validateDisplayList();
	};

	test.setVerticalPageScrollSize = function(argNumber) {
		b_test_root.setVerticalPageScrollSize(argNumber);
	};

	test.getVerticalPageScrollSize = function() {
		return b_test_root.getVerticalPageScrollSize();
	};

	test.Container = function() {
		return b_test_root.Container();
	};

	test.getBaselinePosition = function() {
		return b_test_root.getBaselinePosition();
	};

	test.getChildDescriptors = function() {
		return b_test_root.getChildDescriptors();
	};

	test.setData = function(argObject) {
		b_test_root.setData(argObject);
	};

	test.getData = function() {
		return b_test_root.getData();
	};

	test.getChildAt = function(argInt) {
		return b_test_root.getChildAt(argInt);
	};

	test.removeChildAt = function(argInt) {
		return b_test_root.removeChildAt(argInt);
	};

	test.contentToGlobal = function(argPoint) {
		return b_test_root.contentToGlobal(argPoint);
	};

	test.getChildIndex = function(argDisplayObject) {
		return b_test_root.getChildIndex(argDisplayObject);
	};

	test.initialize = function() {
		b_test_root.initialize();
	};

	test.getMaxHorizontalScrollPosition = function() {
		return b_test_root.getMaxHorizontalScrollPosition();
	};

	test.getViewMetrics = function() {
		return b_test_root.getViewMetrics();
	};

	test.getRawChildren = function() {
		return b_test_root.getRawChildren();
	};

	test.executeChildBindings = function(argBoolean) {
		b_test_root.executeChildBindings(argBoolean);
	};

	test.getHorizontalLineScrollSize = function() {
		return b_test_root.getHorizontalLineScrollSize();
	};

	test.setHorizontalLineScrollSize = function(argNumber) {
		b_test_root.setHorizontalLineScrollSize(argNumber);
	};

	test.setClipContent = function(argBoolean) {
		b_test_root.setClipContent(argBoolean);
	};

	test.getClipContent = function() {
		return b_test_root.getClipContent();
	};

	test.createComponentFromDescriptor = function(argComponentDescriptor, argBoolean) {
		return b_test_root.createComponentFromDescriptor(argComponentDescriptor, argBoolean);
	};

	test.getDefaultButton = function() {
		return b_test_root.getDefaultButton();
	};

	test.setDefaultButton = function(argIFlexDisplayObject) {
		b_test_root.setDefaultButton(argIFlexDisplayObject);
	};

	test.executeBindings = function(argBoolean) {
		b_test_root.executeBindings(argBoolean);
	};

	test.setVerticalScrollBar = function(argScrollBar) {
		b_test_root.setVerticalScrollBar(argScrollBar);
	};

	test.getVerticalScrollBar = function() {
		return b_test_root.getVerticalScrollBar();
	};

	test.addChild = function(argDisplayObject) {
		return b_test_root.addChild(argDisplayObject);
	};

	test.getHorizontalScrollBar = function() {
		return b_test_root.getHorizontalScrollBar();
	};

	test.setHorizontalScrollBar = function(argScrollBar) {
		b_test_root.setHorizontalScrollBar(argScrollBar);
	};

	test.addChildAt = function(argDisplayObject, argInt) {
		return b_test_root.addChildAt(argDisplayObject, argInt);
	};

	test.setCreationIndex = function(argInt) {
		b_test_root.setCreationIndex(argInt);
	};

	test.getCreationIndex = function() {
		return b_test_root.getCreationIndex();
	};

	test.getFocusPane = function() {
		return b_test_root.getFocusPane();
	};

	test.setFocusPane = function(argSprite) {
		b_test_root.setFocusPane(argSprite);
	};

	test.validateSize = function(argBoolean) {
		b_test_root.validateSize(argBoolean);
	};

	test.removeAllChildren = function() {
		b_test_root.removeAllChildren();
	};

	test.prepareToPrint = function(argIFlexDisplayObject) {
		return b_test_root.prepareToPrint(argIFlexDisplayObject);
	};

	test.setWidth = function(argNumber) {
		b_test_root.setWidth(argNumber);
	};

	test.getWidth = function() {
		return b_test_root.getWidth();
	};

	test.getHeight = function() {
		return b_test_root.getHeight();
	};

	test.setHeight = function(argNumber) {
		b_test_root.setHeight(argNumber);
	};

	test.getRect = function(argDisplayObject) {
		return b_test_root.getRect(argDisplayObject);
	};

	test.getScale9Grid = function() {
		return b_test_root.getScale9Grid();
	};

	test.setScale9Grid = function(argRectangle) {
		b_test_root.setScale9Grid(argRectangle);
	};

	test.hitTestObject = function(argDisplayObject) {
		return b_test_root.hitTestObject(argDisplayObject);
	};

	test.getBounds = function(argDisplayObject) {
		return b_test_root.getBounds(argDisplayObject);
	};

	test.hitTestPoint = function(argNumber1, argNumber2, argBoolean) {
		return b_test_root.hitTestPoint(argNumber1, argNumber2, argBoolean);
	};

	test.getStage = function() {
		return b_test_root.getStage();
	};

	test.getParent = function() {
		return b_test_root.getParent();
	};

	test.localToGlobal = function(argPoint) {
		return b_test_root.localToGlobal(argPoint);
	};

	test.getLoaderInfo = function() {
		return b_test_root.getLoaderInfo();
	};

	test.getName = function() {
		return b_test_root.getName();
	};

	test.setName = function(argString) {
		b_test_root.setName(argString);
	};

	test.getOpaqueBackground = function() {
		return b_test_root.getOpaqueBackground();
	};

	test.setOpaqueBackground = function(argObject) {
		b_test_root.setOpaqueBackground(argObject);
	};

	test.getCacheAsBitmap = function() {
		return b_test_root.getCacheAsBitmap();
	};

	test.setCacheAsBitmap = function(argBoolean) {
		b_test_root.setCacheAsBitmap(argBoolean);
	};

	test.setFilters = function(argArray) {
		b_test_root.setFilters(argArray);
	};

	test.getFilters = function() {
		return b_test_root.getFilters();
	};

	test.getAccessibilityProperties = function() {
		return b_test_root.getAccessibilityProperties();
	};

	test.setAccessibilityProperties = function(argAccessibilityProperties) {
		b_test_root.setAccessibilityProperties(argAccessibilityProperties);
	};

	test.setVisible = function(argBoolean) {
		b_test_root.setVisible(argBoolean);
	};

	test.getVisible = function() {
		return b_test_root.getVisible();
	};

	test.getRoot = function() {
		return b_test_root.getRoot();
	};

	test.getTransform = function() {
		return b_test_root.getTransform();
	};

	test.setTransform = function(argTransform) {
		b_test_root.setTransform(argTransform);
	};

	test.setRotation = function(argNumber) {
		b_test_root.setRotation(argNumber);
	};

	test.getRotation = function() {
		return b_test_root.getRotation();
	};

	test.setScaleY = function(argNumber) {
		b_test_root.setScaleY(argNumber);
	};

	test.getScaleY = function() {
		return b_test_root.getScaleY();
	};

	test.setScaleX = function(argNumber) {
		b_test_root.setScaleX(argNumber);
	};

	test.getScaleX = function() {
		return b_test_root.getScaleX();
	};

	test.getMouseY = function() {
		return b_test_root.getMouseY();
	};

	test.getMouseX = function() {
		return b_test_root.getMouseX();
	};

	test.getY = function() {
		return b_test_root.getY();
	};

	test.setY = function(argNumber) {
		b_test_root.setY(argNumber);
	};

	test.setX = function(argNumber) {
		b_test_root.setX(argNumber);
	};

	test.getX = function() {
		return b_test_root.getX();
	};

	test.DisplayObject = function() {
		return b_test_root.DisplayObject();
	};

	test.setMask = function(argDisplayObject) {
		b_test_root.setMask(argDisplayObject);
	};

	test.getMask = function() {
		return b_test_root.getMask();
	};

	test.setAlpha = function(argNumber) {
		b_test_root.setAlpha(argNumber);
	};

	test.getAlpha = function() {
		return b_test_root.getAlpha();
	};

	test.setScrollRect = function(argRectangle) {
		b_test_root.setScrollRect(argRectangle);
	};

	test.getScrollRect = function() {
		return b_test_root.getScrollRect();
	};

	test.getBlendMode = function() {
		return b_test_root.getBlendMode();
	};

	test.setBlendMode = function(argString) {
		b_test_root.setBlendMode(argString);
	};

	test.globalToLocal = function(argPoint) {
		return b_test_root.globalToLocal(argPoint);
	};

	test.getChildIndex = function(argDisplayObject) {
		return b_test_root.getChildIndex(argDisplayObject);
	};

	test.getChildByName = function(argString) {
		return b_test_root.getChildByName(argString);
	};

	test.getNumChildren = function() {
		return b_test_root.getNumChildren();
	};

	test.setChildIndex = function(argDisplayObject, argInt) {
		b_test_root.setChildIndex(argDisplayObject, argInt);
	};

	test.getTabChildren = function() {
		return b_test_root.getTabChildren();
	};

	test.setTabChildren = function(argBoolean) {
		b_test_root.setTabChildren(argBoolean);
	};

	test.addChild = function(argDisplayObject) {
		return b_test_root.addChild(argDisplayObject);
	};

	test.swapChildren = function(argDisplayObject1, argDisplayObject2) {
		b_test_root.swapChildren(argDisplayObject1, argDisplayObject2);
	};

	test.removeChild = function(argDisplayObject) {
		return b_test_root.removeChild(argDisplayObject);
	};

	test.contains = function(argDisplayObject) {
		return b_test_root.contains(argDisplayObject);
	};

	test.removeChildAt = function(argInt) {
		return b_test_root.removeChildAt(argInt);
	};

	test.getTextSnapshot = function() {
		return b_test_root.getTextSnapshot();
	};

	test.swapChildrenAt = function(argInt1, argInt2) {
		b_test_root.swapChildrenAt(argInt1, argInt2);
	};

	test.setMouseChildren = function(argBoolean) {
		b_test_root.setMouseChildren(argBoolean);
	};

	test.getMouseChildren = function() {
		return b_test_root.getMouseChildren();
	};

	test.areInaccessibleObjectsUnderPoint = function(argPoint) {
		return b_test_root.areInaccessibleObjectsUnderPoint(argPoint);
	};

	test.DisplayObjectContainer = function() {
		return b_test_root.DisplayObjectContainer();
	};

	test.getChildAt = function(argInt) {
		return b_test_root.getChildAt(argInt);
	};

	test.getObjectsUnderPoint = function(argPoint) {
		return b_test_root.getObjectsUnderPoint(argPoint);
	};

	test.addChildAt = function(argDisplayObject, argInt) {
		return b_test_root.addChildAt(argDisplayObject, argInt);
	};

	test.willTrigger = function(argString) {
		return b_test_root.willTrigger(argString);
	};

	test.toString = function() {
		return b_test_root.toString();
	};

	test.removeEventListener = function(argString, argFunction, argBoolean) {
		b_test_root.removeEventListener(argString, argFunction, argBoolean);
	};

	test.EventDispatcher = function(argIEventDispatcher) {
		return b_test_root.EventDispatcher(argIEventDispatcher);
	};

	test.addEventListener = function(argString, argFunction, argBoolean1, argInt, argBoolean2) {
		b_test_root.addEventListener(argString, argFunction, argBoolean1, argInt, argBoolean2);
	};

	test.hasEventListener = function(argString) {
		return b_test_root.hasEventListener(argString);
	};

	test.dispatchEvent = function(argEvent) {
		return b_test_root.dispatchEvent(argEvent);
	};

	test.FlexSprite = function() {
		return b_test_root.FlexSprite();
	};

	test.toString = function() {
		return b_test_root.toString();
	};

	test.getTabEnabled = function() {
		return b_test_root.getTabEnabled();
	};

	test.setTabEnabled = function(argBoolean) {
		b_test_root.setTabEnabled(argBoolean);
	};

	test.getTabIndex = function() {
		return b_test_root.getTabIndex();
	};

	test.setTabIndex = function(argInt) {
		b_test_root.setTabIndex(argInt);
	};

	test.getAccessibilityImplementation = function() {
		return b_test_root.getAccessibilityImplementation();
	};

	test.setAccessibilityImplementation = function(argAccessibilityImplementation) {
		b_test_root.setAccessibilityImplementation(argAccessibilityImplementation);
	};

	test.getDoubleClickEnabled = function() {
		return b_test_root.getDoubleClickEnabled();
	};

	test.setDoubleClickEnabled = function(argBoolean) {
		b_test_root.setDoubleClickEnabled(argBoolean);
	};

	test.getMouseEnabled = function() {
		return b_test_root.getMouseEnabled();
	};

	test.setMouseEnabled = function(argBoolean) {
		b_test_root.setMouseEnabled(argBoolean);
	};

	test.InteractiveObject = function() {
		return b_test_root.InteractiveObject();
	};

	test.setFocusRect = function(argObject) {
		b_test_root.setFocusRect(argObject);
	};

	test.getFocusRect = function() {
		return b_test_root.getFocusRect();
	};

	test.setContextMenu = function(argContextMenu) {
		b_test_root.setContextMenu(argContextMenu);
	};

	test.getContextMenu = function() {
		return b_test_root.getContextMenu();
	};

	test.LayoutContainer = function() {
		return b_test_root.LayoutContainer();
	};

	test.getConstraintColumns = function() {
		return b_test_root.getConstraintColumns();
	};

	test.setConstraintColumns = function(argArray) {
		b_test_root.setConstraintColumns(argArray);
	};

	test.setLayout = function(argString) {
		b_test_root.setLayout(argString);
	};

	test.getLayout = function() {
		return b_test_root.getLayout();
	};

	test.getConstraintRows = function() {
		return b_test_root.getConstraintRows();
	};

	test.setConstraintRows = function(argArray) {
		b_test_root.setConstraintRows(argArray);
	};

	test.toString = function() {
		return b_test_root.toString();
	};

	test.hasOwnProperty = function(argString) {
		return b_test_root.hasOwnProperty(argString);
	};

	test.isPrototypeOf = function(argObject) {
		return b_test_root.isPrototypeOf(argObject);
	};

	test.propertyIsEnumerable = function(argString) {
		return b_test_root.propertyIsEnumerable(argString);
	};

	test.Object = function() {
		return b_test_root.Object();
	};

	test.toLocaleString = function() {
		return b_test_root.toLocaleString();
	};

	test.setPropertyIsEnumerable = function(argString, argBoolean) {
		b_test_root.setPropertyIsEnumerable(argString, argBoolean);
	};

	test.valueOf = function() {
		return b_test_root.valueOf();
	};

	test.getDropTarget = function() {
		return b_test_root.getDropTarget();
	};

	test.setHitArea = function(argSprite) {
		b_test_root.setHitArea(argSprite);
	};

	test.getHitArea = function() {
		return b_test_root.getHitArea();
	};

	test.Sprite = function() {
		return b_test_root.Sprite();
	};

	test.setUseHandCursor = function(argBoolean) {
		b_test_root.setUseHandCursor(argBoolean);
	};

	test.getUseHandCursor = function() {
		return b_test_root.getUseHandCursor();
	};

	test.stopDrag = function() {
		b_test_root.stopDrag();
	};

	test.startDrag = function(argBoolean, argRectangle) {
		b_test_root.startDrag(argBoolean, argRectangle);
	};

	test.setButtonMode = function(argBoolean) {
		b_test_root.setButtonMode(argBoolean);
	};

	test.getButtonMode = function() {
		return b_test_root.getButtonMode();
	};

	test.getSoundTransform = function() {
		return b_test_root.getSoundTransform();
	};

	test.setSoundTransform = function(argSoundTransform) {
		b_test_root.setSoundTransform(argSoundTransform);
	};

	test.getGraphics = function() {
		return b_test_root.getGraphics();
	};

	test.regenerateStyleCache = function(argBoolean) {
		b_test_root.regenerateStyleCache(argBoolean);
	};

	test.initialize = function() {
		b_test_root.initialize();
	};

	test.getAutomationTabularData = function() {
		return b_test_root.getAutomationTabularData();
	};

	test.getUid = function() {
		return b_test_root.getUid();
	};

	test.setUid = function(argString) {
		b_test_root.setUid(argString);
	};

	test.getScaleY = function() {
		return b_test_root.getScaleY();
	};

	test.setScaleY = function(argNumber) {
		b_test_root.setScaleY(argNumber);
	};

	test.getScaleX = function() {
		return b_test_root.getScaleX();
	};

	test.setScaleX = function(argNumber) {
		b_test_root.setScaleX(argNumber);
	};

	test.getRepeaterItem = function(argInt) {
		return b_test_root.getRepeaterItem(argInt);
	};

	test.setStyleDeclaration = function(argCSSStyleDeclaration) {
		b_test_root.setStyleDeclaration(argCSSStyleDeclaration);
	};

	test.getStyleDeclaration = function() {
		return b_test_root.getStyleDeclaration();
	};

	test.setMaxWidth = function(argNumber) {
		b_test_root.setMaxWidth(argNumber);
	};

	test.getMaxWidth = function() {
		return b_test_root.getMaxWidth();
	};

	test.measureHTMLText = function(argString) {
		return b_test_root.measureHTMLText(argString);
	};

	test.setSystemManager = function(argISystemManager) {
		b_test_root.setSystemManager(argISystemManager);
	};

	test.getSystemManager = function() {
		return b_test_root.getSystemManager();
	};

	test.validateDisplayList = function() {
		b_test_root.validateDisplayList();
	};

	test.setMinWidth = function(argNumber) {
		b_test_root.setMinWidth(argNumber);
	};

	test.getMinWidth = function() {
		return b_test_root.getMinWidth();
	};

	test.getExplicitOrMeasuredWidth = function() {
		return b_test_root.getExplicitOrMeasuredWidth();
	};

	test.setInitialized = function(argBoolean) {
		b_test_root.setInitialized(argBoolean);
	};

	test.getInitialized = function() {
		return b_test_root.getInitialized();
	};

	test.contentToGlobal = function(argPoint) {
		return b_test_root.contentToGlobal(argPoint);
	};

	test.getAutomationValue = function() {
		return b_test_root.getAutomationValue();
	};

	test.getExplicitHeight = function() {
		return b_test_root.getExplicitHeight();
	};

	test.setExplicitHeight = function(argNumber) {
		b_test_root.setExplicitHeight(argNumber);
	};

	test.executeBindings = function(argBoolean) {
		b_test_root.executeBindings(argBoolean);
	};

	test.getPercentWidth = function() {
		return b_test_root.getPercentWidth();
	};

	test.setPercentWidth = function(argNumber) {
		b_test_root.setPercentWidth(argNumber);
	};

	test.getModuleFactory = function() {
		return b_test_root.getModuleFactory();
	};

	test.setModuleFactory = function(argIFlexModuleFactory) {
		b_test_root.setModuleFactory(argIFlexModuleFactory);
	};

	test.getParentApplication = function() {
		return b_test_root.getParentApplication();
	};

	test.drawRoundRect = function(argNumber1, argNumber2, argNumber3, argNumber4, argObject5, argObject6, argObject7, argObject8, argString, argArray, argObject9) {
		b_test_root.drawRoundRect(argNumber1, argNumber2, argNumber3, argNumber4, argObject5, argObject6, argObject7, argObject8, argString, argArray, argObject9);
	};

	test.resolveAutomationIDPart = function(argObject) {
		return b_test_root.resolveAutomationIDPart(argObject);
	};

	test.setChildIndex = function(argDisplayObject, argInt) {
		b_test_root.setChildIndex(argDisplayObject, argInt);
	};

	test.setUpdateCompletePendingFlag = function(argBoolean) {
		b_test_root.setUpdateCompletePendingFlag(argBoolean);
	};

	test.getUpdateCompletePendingFlag = function() {
		return b_test_root.getUpdateCompletePendingFlag();
	};

	test.setProcessedDescriptors = function(argBoolean) {
		b_test_root.setProcessedDescriptors(argBoolean);
	};

	test.getProcessedDescriptors = function() {
		return b_test_root.getProcessedDescriptors();
	};

	test.setDoubleClickEnabled = function(argBoolean) {
		b_test_root.setDoubleClickEnabled(argBoolean);
	};

	test.getDoubleClickEnabled = function() {
		return b_test_root.getDoubleClickEnabled();
	};

	test.setActualSize = function(argNumber1, argNumber2) {
		b_test_root.setActualSize(argNumber1, argNumber2);
	};

	test.setOwner = function(argDisplayObjectContainer) {
		b_test_root.setOwner(argDisplayObjectContainer);
	};

	test.getOwner = function() {
		return b_test_root.getOwner();
	};

	test.measureText = function(argString) {
		return b_test_root.measureText(argString);
	};

	test.getRepeaters = function() {
		return b_test_root.getRepeaters();
	};

	test.setRepeaters = function(argArray) {
		b_test_root.setRepeaters(argArray);
	};

	test.notifyStyleChangeInChildren = function(argString, argBoolean) {
		b_test_root.notifyStyleChangeInChildren(argString, argBoolean);
	};

	test.setStyle = function(argString, argObject) {
		b_test_root.setStyle(argString, argObject);
	};

	test.getFlexContextMenu = function() {
		return b_test_root.getFlexContextMenu();
	};

	test.setFlexContextMenu = function(argIFlexContextMenu) {
		b_test_root.setFlexContextMenu(argIFlexContextMenu);
	};

	test.createReferenceOnParentDocument = function(argIFlexDisplayObject) {
		b_test_root.createReferenceOnParentDocument(argIFlexDisplayObject);
	};

	test.getMouseFocusEnabled = function() {
		return b_test_root.getMouseFocusEnabled();
	};

	test.setMouseFocusEnabled = function(argBoolean) {
		b_test_root.setMouseFocusEnabled(argBoolean);
	};

	test.stopDrag = function() {
		b_test_root.stopDrag();
	};

	test.localToContent = function(argPoint) {
		return b_test_root.localToContent(argPoint);
	};

	test.prepareToPrint = function(argIFlexDisplayObject) {
		return b_test_root.prepareToPrint(argIFlexDisplayObject);
	};

	test.endEffectsStarted = function() {
		b_test_root.endEffectsStarted();
	};

	test.registerEffects = function(argArray) {
		b_test_root.registerEffects(argArray);
	};

	test.getActiveEffects = function() {
		return b_test_root.getActiveEffects();
	};

	test.getFocusPane = function() {
		return b_test_root.getFocusPane();
	};

	test.setFocusPane = function(argSprite) {
		b_test_root.setFocusPane(argSprite);
	};

	test.getInheritingStyles = function() {
		return b_test_root.getInheritingStyles();
	};

	test.setInheritingStyles = function(argObject) {
		b_test_root.setInheritingStyles(argObject);
	};

	test.verticalGradientMatrix = function(argNumber1, argNumber2, argNumber3, argNumber4) {
		return b_test_root.verticalGradientMatrix(argNumber1, argNumber2, argNumber3, argNumber4);
	};

	test.determineTextFormatFromStyles = function() {
		return b_test_root.determineTextFormatFromStyles();
	};

	test.setMaxHeight = function(argNumber) {
		b_test_root.setMaxHeight(argNumber);
	};

	test.getMaxHeight = function() {
		return b_test_root.getMaxHeight();
	};

	test.getBaselinePosition = function() {
		return b_test_root.getBaselinePosition();
	};

	test.callLater = function(argFunction, argArray) {
		b_test_root.callLater(argFunction, argArray);
	};

	test.hasFontContextChanged = function() {
		return b_test_root.hasFontContextChanged();
	};

	test.setDescriptor = function(argUIComponentDescriptor) {
		b_test_root.setDescriptor(argUIComponentDescriptor);
	};

	test.getDescriptor = function() {
		return b_test_root.getDescriptor();
	};

	test.deleteReferenceOnParentDocument = function(argIFlexDisplayObject) {
		b_test_root.deleteReferenceOnParentDocument(argIFlexDisplayObject);
	};

	test.getErrorString = function() {
		return b_test_root.getErrorString();
	};

	test.setErrorString = function(argString) {
		b_test_root.setErrorString(argString);
	};

	test.move = function(argNumber1, argNumber2) {
		b_test_root.move(argNumber1, argNumber2);
	};

	test.getWidth = function() {
		return b_test_root.getWidth();
	};

	test.setWidth = function(argNumber) {
		b_test_root.setWidth(argNumber);
	};

	test.getInstanceIndex = function() {
		return b_test_root.getInstanceIndex();
	};

	test.getClassStyleDeclarations = function() {
		return b_test_root.getClassStyleDeclarations();
	};

	test.initializeRepeaterArrays = function(argIRepeaterClient) {
		b_test_root.initializeRepeaterArrays(argIRepeaterClient);
	};

	test.getExplicitMaxWidth = function() {
		return b_test_root.getExplicitMaxWidth();
	};

	test.setExplicitMaxWidth = function(argNumber) {
		b_test_root.setExplicitMaxWidth(argNumber);
	};

	test.setExplicitMinHeight = function(argNumber) {
		b_test_root.setExplicitMinHeight(argNumber);
	};

	test.getExplicitMinHeight = function() {
		return b_test_root.getExplicitMinHeight();
	};

	test.clearStyle = function(argString) {
		b_test_root.clearStyle(argString);
	};

	test.invalidateProperties = function() {
		b_test_root.invalidateProperties();
	};

	test.setCacheHeuristic = function(argBoolean) {
		b_test_root.setCacheHeuristic(argBoolean);
	};

	test.getFilters = function() {
		return b_test_root.getFilters();
	};

	test.setFilters = function(argArray) {
		b_test_root.setFilters(argArray);
	};

	test.validateProperties = function() {
		b_test_root.validateProperties();
	};

	test.getIncludeInLayout = function() {
		return b_test_root.getIncludeInLayout();
	};

	test.setIncludeInLayout = function(argBoolean) {
		b_test_root.setIncludeInLayout(argBoolean);
	};

	test.addChildAt = function(argDisplayObject, argInt) {
		return b_test_root.addChildAt(argDisplayObject, argInt);
	};

	test.getAutomationName = function() {
		return b_test_root.getAutomationName();
	};

	test.setAutomationName = function(argString) {
		b_test_root.setAutomationName(argString);
	};

	test.getClassName = function() {
		return b_test_root.getClassName();
	};

	test.getNonInheritingStyles = function() {
		return b_test_root.getNonInheritingStyles();
	};

	test.setNonInheritingStyles = function(argObject) {
		b_test_root.setNonInheritingStyles(argObject);
	};

	test.setExplicitWidth = function(argNumber) {
		b_test_root.setExplicitWidth(argNumber);
	};

	test.getExplicitWidth = function() {
		return b_test_root.getExplicitWidth();
	};

	test.setMinHeight = function(argNumber) {
		b_test_root.setMinHeight(argNumber);
	};

	test.getMinHeight = function() {
		return b_test_root.getMinHeight();
	};

	test.dispatchEvent = function(argEvent) {
		return b_test_root.dispatchEvent(argEvent);
	};

	test.getExplicitMinWidth = function() {
		return b_test_root.getExplicitMinWidth();
	};

	test.setExplicitMinWidth = function(argNumber) {
		b_test_root.setExplicitMinWidth(argNumber);
	};

	test.getStyle = function(argString) {
		return b_test_root.getStyle(argString);
	};

	test.getMouseY = function() {
		return b_test_root.getMouseY();
	};

	test.getMouseX = function() {
		return b_test_root.getMouseX();
	};

	test.getScreen = function() {
		return b_test_root.getScreen();
	};

	test.getExplicitOrMeasuredHeight = function() {
		return b_test_root.getExplicitOrMeasuredHeight();
	};

	test.setFocus = function() {
		b_test_root.setFocus();
	};

	test.horizontalGradientMatrix = function(argNumber1, argNumber2, argNumber3, argNumber4) {
		return b_test_root.horizontalGradientMatrix(argNumber1, argNumber2, argNumber3, argNumber4);
	};

	test.setConstraintValue = function(argString, argObject) {
		b_test_root.setConstraintValue(argString, argObject);
	};

	test.getInstanceIndices = function() {
		return b_test_root.getInstanceIndices();
	};

	test.setInstanceIndices = function(argArray) {
		b_test_root.setInstanceIndices(argArray);
	};

	test.setRepeaterIndices = function(argArray) {
		b_test_root.setRepeaterIndices(argArray);
	};

	test.getRepeaterIndices = function() {
		return b_test_root.getRepeaterIndices();
	};

	test.setTweeningProperties = function(argArray) {
		b_test_root.setTweeningProperties(argArray);
	};

	test.getTweeningProperties = function() {
		return b_test_root.getTweeningProperties();
	};

	test.setCachePolicy = function(argString) {
		b_test_root.setCachePolicy(argString);
	};

	test.getCachePolicy = function() {
		return b_test_root.getCachePolicy();
	};

	test.addChild = function(argDisplayObject) {
		return b_test_root.addChild(argDisplayObject);
	};

	test.invalidateSize = function() {
		b_test_root.invalidateSize();
	};

	test.setVisible = function(argBoolean1, argBoolean2) {
		b_test_root.setVisible(argBoolean1, argBoolean2);
	};

	test.parentChanged = function(argDisplayObjectContainer) {
		b_test_root.parentChanged(argDisplayObjectContainer);
	};

	test.setMeasuredHeight = function(argNumber) {
		b_test_root.setMeasuredHeight(argNumber);
	};

	test.getMeasuredHeight = function() {
		return b_test_root.getMeasuredHeight();
	};

	test.removeChild = function(argDisplayObject) {
		return b_test_root.removeChild(argDisplayObject);
	};

	test.validateNow = function() {
		b_test_root.validateNow();
	};

	test.invalidateDisplayList = function() {
		b_test_root.invalidateDisplayList();
	};

	test.setMeasuredWidth = function(argNumber) {
		b_test_root.setMeasuredWidth(argNumber);
	};

	test.getMeasuredWidth = function() {
		return b_test_root.getMeasuredWidth();
	};

	test.getAutomationChildAt = function(argInt) {
		return b_test_root.getAutomationChildAt(argInt);
	};

	test.getPercentHeight = function() {
		return b_test_root.getPercentHeight();
	};

	test.setPercentHeight = function(argNumber) {
		b_test_root.setPercentHeight(argNumber);
	};

	test.getIsPopUp = function() {
		return b_test_root.getIsPopUp();
	};

	test.setIsPopUp = function(argBoolean) {
		b_test_root.setIsPopUp(argBoolean);
	};

	test.getId = function() {
		return b_test_root.getId();
	};

	test.setId = function(argString) {
		b_test_root.setId(argString);
	};

	test.setStyleName = function(argObject) {
		b_test_root.setStyleName(argObject);
	};

	test.getStyleName = function() {
		return b_test_root.getStyleName();
	};

	test.globalToContent = function(argPoint) {
		return b_test_root.globalToContent(argPoint);
	};

	test.getIsDocument = function() {
		return b_test_root.getIsDocument();
	};

	test.setCacheAsBitmap = function(argBoolean) {
		b_test_root.setCacheAsBitmap(argBoolean);
	};

	test.getRepeaterIndex = function() {
		return b_test_root.getRepeaterIndex();
	};

	test.getParent = function() {
		return b_test_root.getParent();
	};

	test.getRepeater = function() {
		return b_test_root.getRepeater();
	};

	test.getMeasuredMinHeight = function() {
		return b_test_root.getMeasuredMinHeight();
	};

	test.setMeasuredMinHeight = function(argNumber) {
		b_test_root.setMeasuredMinHeight(argNumber);
	};

	test.getVisibleRect = function(argDisplayObject) {
		return b_test_root.getVisibleRect(argDisplayObject);
	};

	test.setFocusManager = function(argIFocusManager) {
		b_test_root.setFocusManager(argIFocusManager);
	};

	test.getFocusManager = function() {
		return b_test_root.getFocusManager();
	};

	test.effectStarted = function(argIEffectInstance) {
		b_test_root.effectStarted(argIEffectInstance);
	};

	test.UIComponent = function() {
		return b_test_root.UIComponent();
	};

	test.getDocument = function() {
		return b_test_root.getDocument();
	};

	test.setDocument = function(argObject) {
		b_test_root.setDocument(argObject);
	};

	test.getFocus = function() {
		return b_test_root.getFocus();
	};

	test.validationResultHandler = function(argValidationResultEvent) {
		b_test_root.validationResultHandler(argValidationResultEvent);
	};

	test.setCurrentState = function(argString, argBoolean) {
		b_test_root.setCurrentState(argString, argBoolean);
	};

	test.finishPrint = function(argObject, argIFlexDisplayObject) {
		b_test_root.finishPrint(argObject, argIFlexDisplayObject);
	};

	test.contentToLocal = function(argPoint) {
		return b_test_root.contentToLocal(argPoint);
	};

	test.validateSize = function(argBoolean) {
		b_test_root.validateSize(argBoolean);
	};

	test.setEnabled = function(argBoolean) {
		b_test_root.setEnabled(argBoolean);
	};

	test.getEnabled = function() {
		return b_test_root.getEnabled();
	};

	test.getNestLevel = function() {
		return b_test_root.getNestLevel();
	};

	test.setNestLevel = function(argInt) {
		b_test_root.setNestLevel(argInt);
	};

	test.getCursorManager = function() {
		return b_test_root.getCursorManager();
	};

	test.setValidationSubField = function(argString) {
		b_test_root.setValidationSubField(argString);
	};

	test.getValidationSubField = function() {
		return b_test_root.getValidationSubField();
	};

	test.styleChanged = function(argString) {
		b_test_root.styleChanged(argString);
	};

	test.setAlpha = function(argNumber) {
		b_test_root.setAlpha(argNumber);
	};

	test.getVisible = function() {
		return b_test_root.getVisible();
	};

	test.setVisible = function(argBoolean) {
		b_test_root.setVisible(argBoolean);
	};

	test.getHeight = function() {
		return b_test_root.getHeight();
	};

	test.setHeight = function(argNumber) {
		b_test_root.setHeight(argNumber);
	};

	test.getY = function() {
		return b_test_root.getY();
	};

	test.setY = function(argNumber) {
		b_test_root.setY(argNumber);
	};

	test.removeChildAt = function(argInt) {
		return b_test_root.removeChildAt(argInt);
	};

	test.getX = function() {
		return b_test_root.getX();
	};

	test.setX = function(argNumber) {
		b_test_root.setX(argNumber);
	};

	test.setAutomationDelegate = function(argObject) {
		b_test_root.setAutomationDelegate(argObject);
	};

	test.getAutomationDelegate = function() {
		return b_test_root.getAutomationDelegate();
	};

	test.getConstraintValue = function(argString) {
		return b_test_root.getConstraintValue(argString);
	};

	test.replayAutomatableEvent = function(argEvent) {
		return b_test_root.replayAutomatableEvent(argEvent);
	};

	test.setMeasuredMinWidth = function(argNumber) {
		b_test_root.setMeasuredMinWidth(argNumber);
	};

	test.getMeasuredMinWidth = function() {
		return b_test_root.getMeasuredMinWidth();
	};

	test.getToolTip = function() {
		return b_test_root.getToolTip();
	};

	test.setToolTip = function(argString) {
		b_test_root.setToolTip(argString);
	};

	test.getNumAutomationChildren = function() {
		return b_test_root.getNumAutomationChildren();
	};

	test.getParentDocument = function() {
		return b_test_root.getParentDocument();
	};

	test.stylesInitialized = function() {
		b_test_root.stylesInitialized();
	};

	test.effectFinished = function(argIEffectInstance) {
		b_test_root.effectFinished(argIEffectInstance);
	};

	test.getContentMouseY = function() {
		return b_test_root.getContentMouseY();
	};

	test.getContentMouseX = function() {
		return b_test_root.getContentMouseX();
	};

	test.setExplicitMaxHeight = function(argNumber) {
		b_test_root.setExplicitMaxHeight(argNumber);
	};

	test.getExplicitMaxHeight = function() {
		return b_test_root.getExplicitMaxHeight();
	};

	test.createAutomationIDPart = function(argIAutomationObject) {
		return b_test_root.createAutomationIDPart(argIAutomationObject);
	};

	test.setCurrentState = function(argString) {
		b_test_root.setCurrentState(argString);
	};

	test.getCurrentState = function() {
		return b_test_root.getCurrentState();
	};

	test.owns = function(argDisplayObject) {
		return b_test_root.owns(argDisplayObject);
	};

	test.setShowInAutomationHierarchy = function(argBoolean) {
		b_test_root.setShowInAutomationHierarchy(argBoolean);
	};

	test.getShowInAutomationHierarchy = function() {
		return b_test_root.getShowInAutomationHierarchy();
	};

	test.drawFocus = function(argBoolean) {
		b_test_root.drawFocus(argBoolean);
	};

	test.setFocusEnabled = function(argBoolean) {
		b_test_root.setFocusEnabled(argBoolean);
	};

	test.getFocusEnabled = function() {
		return b_test_root.getFocusEnabled();
	};

}
