import { element, by, ElementFinder } from 'protractor';

export class EmpruntComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-emprunt div table .btn-danger'));
  title = element.all(by.css('jhi-emprunt div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class EmpruntUpdatePage {
  pageTitle = element(by.id('jhi-emprunt-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  dateEmpruntInput = element(by.id('field_dateEmprunt'));

  exemplaireSelect = element(by.id('field_exemplaire'));
  userSelect = element(by.id('field_user'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setDateEmpruntInput(dateEmprunt: string): Promise<void> {
    await this.dateEmpruntInput.sendKeys(dateEmprunt);
  }

  async getDateEmpruntInput(): Promise<string> {
    return await this.dateEmpruntInput.getAttribute('value');
  }

  async exemplaireSelectLastOption(): Promise<void> {
    await this.exemplaireSelect.all(by.tagName('option')).last().click();
  }

  async exemplaireSelectOption(option: string): Promise<void> {
    await this.exemplaireSelect.sendKeys(option);
  }

  getExemplaireSelect(): ElementFinder {
    return this.exemplaireSelect;
  }

  async getExemplaireSelectedOption(): Promise<string> {
    return await this.exemplaireSelect.element(by.css('option:checked')).getText();
  }

  async userSelectLastOption(): Promise<void> {
    await this.userSelect.all(by.tagName('option')).last().click();
  }

  async userSelectOption(option: string): Promise<void> {
    await this.userSelect.sendKeys(option);
  }

  getUserSelect(): ElementFinder {
    return this.userSelect;
  }

  async getUserSelectedOption(): Promise<string> {
    return await this.userSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class EmpruntDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-emprunt-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-emprunt'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
