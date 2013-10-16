

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import core.asset.TestCCAsset;
import core.cc.TestAssuranceLevel;
import core.cc.TestIDentification;
import core.cc.TestSecurityTarget;
import core.cc.TestTOEEnvironment;
import core.cc.TestTermAndDefinition;
import core.checker.TestSecurityTargetChecker;
import core.printer.securityTarget.TestSecurityProblemPrinter;
import core.printer.securityTarget.TestSecurityTargetPrinter;



@RunWith(Suite.class)
@SuiteClasses({	TestSecurityTarget.class, 
				TestSecurityTargetChecker.class,
				TestSecurityTargetPrinter.class,
				TestSecurityProblemPrinter.class,
				TestCCAsset.class,
				TestTermAndDefinition.class,
				TestTOEEnvironment.class,
				TestIDentification.class,
				TestAssuranceLevel.class})
public class AllTestsSecurityTarget {

}
