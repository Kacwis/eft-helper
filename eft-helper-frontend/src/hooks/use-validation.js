import { useState } from "react";

const useValidation = (validationCondition) => {
	const [isInputTouched, setIsInputTouched] = useState(false);
	const [inputValue, setInputValue] = useState("");

	const inputBlurHandler = () => {
		setIsInputTouched(true);
	};

	const inputChangeHandler = (event) => {
		setInputValue(event.target.value);
	};

	const cleanInput = () => {
		setIsInputTouched(false);
		setInputValue("");
	};

	return {
		inputValue,
		isInputTouched,
		isInputValid: validationCondition(inputValue),
		inputBlurHandler,
		inputChangeHandler,
		cleanInput,
	};
};

export default useValidation;
