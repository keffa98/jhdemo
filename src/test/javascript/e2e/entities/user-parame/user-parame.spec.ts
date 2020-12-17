import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { UserParameComponentsPage, UserParameDeleteDialog, UserParameUpdatePage } from './user-parame.page-object';

const expect = chai.expect;

describe('UserParame e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let userParameComponentsPage: UserParameComponentsPage;
  let userParameUpdatePage: UserParameUpdatePage;
  let userParameDeleteDialog: UserParameDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load UserParames', async () => {
    await navBarPage.goToEntity('user-parame');
    userParameComponentsPage = new UserParameComponentsPage();
    await browser.wait(ec.visibilityOf(userParameComponentsPage.title), 5000);
    expect(await userParameComponentsPage.getTitle()).to.eq('jhdemoApp.userParame.home.title');
    await browser.wait(ec.or(ec.visibilityOf(userParameComponentsPage.entities), ec.visibilityOf(userParameComponentsPage.noResult)), 1000);
  });

  it('should load create UserParame page', async () => {
    await userParameComponentsPage.clickOnCreateButton();
    userParameUpdatePage = new UserParameUpdatePage();
    expect(await userParameUpdatePage.getPageTitle()).to.eq('jhdemoApp.userParame.home.createOrEditLabel');
    await userParameUpdatePage.cancel();
  });

  it('should create and save UserParames', async () => {
    const nbButtonsBeforeCreate = await userParameComponentsPage.countDeleteButtons();

    await userParameComponentsPage.clickOnCreateButton();

    await promise.all([
      userParameUpdatePage.setTitreInput('titre'),
      userParameUpdatePage.setDescriptionInput('description'),
      userParameUpdatePage.setIsbnInput('isbn'),
      userParameUpdatePage.setCodeInput('code'),
      userParameUpdatePage.userSelectLastOption(),
    ]);

    expect(await userParameUpdatePage.getTitreInput()).to.eq('titre', 'Expected Titre value to be equals to titre');
    expect(await userParameUpdatePage.getDescriptionInput()).to.eq('description', 'Expected Description value to be equals to description');
    expect(await userParameUpdatePage.getIsbnInput()).to.eq('isbn', 'Expected Isbn value to be equals to isbn');
    expect(await userParameUpdatePage.getCodeInput()).to.eq('code', 'Expected Code value to be equals to code');

    await userParameUpdatePage.save();
    expect(await userParameUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await userParameComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last UserParame', async () => {
    const nbButtonsBeforeDelete = await userParameComponentsPage.countDeleteButtons();
    await userParameComponentsPage.clickOnLastDeleteButton();

    userParameDeleteDialog = new UserParameDeleteDialog();
    expect(await userParameDeleteDialog.getDialogTitle()).to.eq('jhdemoApp.userParame.delete.question');
    await userParameDeleteDialog.clickOnConfirmButton();

    expect(await userParameComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
