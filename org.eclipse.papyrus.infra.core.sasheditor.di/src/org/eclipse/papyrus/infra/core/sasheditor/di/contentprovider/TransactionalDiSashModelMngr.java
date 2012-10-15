/**
 * 
 */
package org.eclipse.papyrus.infra.core.sasheditor.di.contentprovider;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.IPageMngr;
import org.eclipse.papyrus.infra.core.sasheditor.contentprovider.ISashWindowsContentProvider;
import org.eclipse.papyrus.infra.core.sasheditor.di.contentprovider.internal.TransactionalDiContentProvider;
import org.eclipse.papyrus.infra.core.sasheditor.di.contentprovider.internal.TransactionalPageMngrImpl;


/**
 * DiSashModelMngr providing transactional commands to modify SashModel.
 * 
 * @author cedric dumoulin
 * 
 */
public class TransactionalDiSashModelMngr extends DiSashModelMngr {

	private TransactionalDiContentProvider transDiContentProvider;

	private TransactionalPageMngrImpl transPageMngrImpl;

	/**
	 * 
	 * Constructor.
	 * 
	 * @param pageModelFactory
	 * @param diResource
	 */
	public TransactionalDiSashModelMngr(IPageModelFactory pageModelFactory, final Resource diResource, TransactionalEditingDomain editingDomain) {
		super(pageModelFactory, false);



		// lookup the SashModel
		sashWindowMngr = lookupSashWindowMngr(diResource);
		// If no SashWindow structure is found, create a new one using a transaction.
		if(sashWindowMngr == null) {
			RecordingCommand command = new RecordingCommand(editingDomain) {

				@Override
				protected void doExecute() {
					// Create a default model and attach it to resource.
					sashWindowMngr = createDefaultSashModel();
					diResource.getContents().add(sashWindowMngr);
				}
			};
			editingDomain.getCommandStack().execute(command);
		}

		// Create the TransactionalDiContentProvider
		transDiContentProvider = new TransactionalDiContentProvider(getDiContentProvider(), editingDomain);

		// Create the TransactionalPageMngrImpl
		transPageMngrImpl = new TransactionalPageMngrImpl(getPageMngrImpl(), editingDomain);
	}

	/**
	 * 
	 * Constructor.
	 * Only create a {@link IPageMngr} impl. Do not create the DiContentProvider as there is no factory provided.
	 * Internal use.
	 * 
	 * @param pageModelFactory
	 * @param diResource
	 */
	private TransactionalDiSashModelMngr(final Resource diResource, TransactionalEditingDomain editingDomain) {
		super(null, false);

		// lookup the SashModel
		sashWindowMngr = lookupSashWindowMngr(diResource);
		// If no SashWindow structure is found, create a new one using a transaction.
		if(sashWindowMngr == null) {
			RecordingCommand command = new RecordingCommand(editingDomain) {

				@Override
				protected void doExecute() {
					// Create a default model and attach it to resource.
					sashWindowMngr = createDefaultSashModel();
					diResource.getContents().add(sashWindowMngr);
				}
			};
			editingDomain.getCommandStack().execute(command);
		}

		// Create the TransactionalPageMngrImpl
		transPageMngrImpl = new TransactionalPageMngrImpl(getPageMngrImpl(), editingDomain);
	}

	/**
	 * Return the transactional version
	 * 
	 * @see org.eclipse.papyrus.infra.core.sasheditor.di.contentprovider.DiSashModelMngr#getISashWindowsContentProvider()
	 * @return
	 * 
	 */
	@Override
	public ISashWindowsContentProvider getISashWindowsContentProvider() {
		return transDiContentProvider;
	}

	/**
	 * Return the transactional version
	 * 
	 * @see org.eclipse.papyrus.infra.core.sasheditor.di.contentprovider.DiSashModelMngr#getIPageMngr()
	 * 
	 * @return
	 */
	@Override
	public IPageMngr getIPageMngr() {
		return transPageMngrImpl;
	}

	/**
	 * Create an instance of IPageMngr acting on the provided resource.
	 * This instance is suitable to add, remove, close or open diagrams.
	 * 
	 * @param diResource
	 * @return The non transactional version of the IPageMngr
	 */
	public static IPageMngr createIPageMngr(Resource diResource, TransactionalEditingDomain editingDomain) {

		// Create an instance of the DiSashModelMngr with no factory.
		// The factory is not needed since we don't get the ISashWindowsContentProvider.
		return new TransactionalDiSashModelMngr(diResource, editingDomain).getIPageMngr();

	}

}
