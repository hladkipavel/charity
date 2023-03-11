document.addEventListener("DOMContentLoaded", function() {

  /**
   * Form Select
   */
  class FormSelect {
    constructor($el) {
      this.$el = $el;
      this.options = [...$el.children];
      this.init();
    }

    init() {
      this.createElements();
      this.addEvents();
      this.$el.parentElement.removeChild(this.$el);
    }

    createElements() {
      // Input for value
      this.valueInput = document.createElement("input");
      this.valueInput.type = "text";
      this.valueInput.name = this.$el.name;

      // Dropdown container
      this.dropdown = document.createElement("div");
      this.dropdown.classList.add("dropdown");

      // List container
      this.ul = document.createElement("ul");

      // All list options
      this.options.forEach((el, i) => {
        const li = document.createElement("li");
        li.dataset.value = el.value;
        li.innerText = el.innerText;

        if (i === 0) {
          // First clickable option
          this.current = document.createElement("div");
          this.current.innerText = el.innerText;
          this.dropdown.appendChild(this.current);
          this.valueInput.value = el.value;
          li.classList.add("selected");
        }

        this.ul.appendChild(li);
      });

      this.dropdown.appendChild(this.ul);
      this.dropdown.appendChild(this.valueInput);
      this.$el.parentElement.appendChild(this.dropdown);
    }

    addEvents() {
      this.dropdown.addEventListener("click", e => {
        const target = e.target;
        this.dropdown.classList.toggle("selecting");

        // Save new value only when clicked on li
        if (target.tagName === "LI") {
          this.valueInput.value = target.dataset.value;
          this.current.innerText = target.innerText;
        }
      });
    }
  }
  document.querySelectorAll(".form-group--dropdown select").forEach(el => {
    new FormSelect(el);
  });

  /**
   * Hide elements when clicked on document
   */
  document.addEventListener("click", function(e) {
    const target = e.target;
    const tagName = target.tagName;

    if (target.classList.contains("dropdown")) return false;

    if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
      return false;
    }

    if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
      return false;
    }

    document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
      el.classList.remove("selecting");
    });
  });

  /**
   * Switching between form steps
   */
  class FormSteps {
    constructor(form) {
      this.$form = form;
      this.$next = form.querySelectorAll(".next-step");
      this.$prev = form.querySelectorAll(".prev-step");
      this.$step = form.querySelector(".form--steps-counter span");
      this.currentStep = 1;

      this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
      const $stepForms = form.querySelectorAll("form > div");
      this.slides = [...this.$stepInstructions, ...$stepForms];

      this.init();
    }

    /**
     * Init all methods
     */
    init() {
      this.events();
      this.updateForm();
    }

    /**
     * All events that are happening in form
     */
    events() {
      // Next step
      this.$next.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep++;
          this.updateForm();
        });
      });

      // Previous step
      this.$prev.forEach(btn => {
        btn.addEventListener("click", e => {
          e.preventDefault();
          this.currentStep--;
          this.updateForm();
        });
      });

      // Form submit
      this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
    }

    /**
     * Update form front-end
     * Show next or previous section etc.
     */
    updateForm() {
      this.$step.innerText = this.currentStep;

      // TODO: Validation
      // STEP 1
      const checkboxes = document.querySelectorAll('input[type=checkbox]');
      const nextButton = document.getElementById('next-button');

      checkboxes.forEach(checkbox=>{
        checkbox.addEventListener('change', ()=>{
          const check = Array.from(checkboxes).some(checkbox => checkbox.checked);
          nextButton.disabled = !check;
        });
      });

      // STEP 2
      const quantity = document.querySelector('input[name=quantity]');
      const nextButton2 = document.getElementById('next-button2');
      quantity.addEventListener('input', ()=>{
        nextButton2.disabled = quantity.value === '';
      });

      // STEP 3
      const radioTypes = document.querySelectorAll('input[type=radio]');
      const nextButton3 = document.getElementById('next-button3');
      radioTypes.forEach(radio =>{
        radio.addEventListener('change', () => {
          nextButton3.disabled = false;
        });
      });

      // STEP 4
      const street = document.querySelector('input[name=street]');
      const city = document.querySelector('input[name=city]');
      const zipCode = document.querySelector('input[name=zipCode]');
      const phone = document.querySelector('input[name=phone]');
      const nextButton4 = document.getElementById('next-button4');
      
      [street, city, zipCode, phone].forEach(el => {
        el.addEventListener('input', () =>{
          const checkAllData = street.value !== '' && city.value !== '' && zipCode.value !== '' && phone.value.match(/^[1-9]{1}\d{8}$/);
          nextButton4.disabled = !checkAllData;
        });
      });

      const currentDate = new Date();
      const year = currentDate.getFullYear();
      const month = String(currentDate.getMonth() + 1).padStart(2, '0');
      const day = String(currentDate.getDate()).padStart(2, '0');
      const minDate = `${year}-${month}-${day}`;
      
      document.querySelector('input[name=pickUpDate]').setAttribute('min', minDate);


      this.slides.forEach(slide => {
        slide.classList.remove("active");

        if (slide.dataset.step == this.currentStep) {
          slide.classList.add("active");
        }
      });

      this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
      this.$step.parentElement.hidden = this.currentStep >= 5;

      // TODO: get data from inputs and show them in summary
      // COUNT BAGS WITH NAME
      const countBags = document.querySelector('input[name=quantity]').value;
      if(countBags == 1){
        document.getElementById('sum-count').innerText = '1 worek: ';
      }else if (countBags > 1 && countBags < 5){
        document.getElementById('sum-count').innerText = countBags + ' worki: ';
      }else{
        document.getElementById('sum-count').innerText = countBags + ' worków: ';
      };
      
      const categories = document.querySelectorAll('input[name=categories]');
      categories.forEach(category => category.addEventListener('change', ()=>{
        category.parentElement.children[2].toggleAttribute('mark');
      }));
      document.getElementById('next-button4').addEventListener('click', ()=>{
        const allMarks = document.querySelectorAll('[mark]');
        const namesOfCategories = [];
        allMarks.forEach(el => namesOfCategories.push(el.innerHTML));
        const sumCategory = document.getElementById('sum-category');
        sumCategory.innerText = namesOfCategories.join('; ');
      });

      // INSTITUTION
      const institutions = document.querySelectorAll('input[name=institution]');
      institutions.forEach(inst => inst.addEventListener('change', ()=>{
        inst.parentElement.children[2].children[0].toggleAttribute('markInst');
      }));
      document.getElementById('next-button4').addEventListener('click', ()=>{
        const sumInst = document.getElementById('sum-institution');
        console.log(sumInst);
        const markInst = document.querySelector('[markInst]');
        console.log(markInst);
        sumInst.innerText = markInst.innerHTML;
      });
      

    }

  }
  const form = document.querySelector(".form--steps");
  if (form !== null) {
    new FormSteps(form);
  }
});
